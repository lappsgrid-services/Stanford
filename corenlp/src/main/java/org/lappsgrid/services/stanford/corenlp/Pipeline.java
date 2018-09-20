package org.lappsgrid.services.stanford.corenlp;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.Pair;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.LifException;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;
import org.lappsgrid.vocabulary.Features;

import static org.lappsgrid.discriminator.Discriminators.*;

/**
 *
 */
public class Pipeline
{
	StanfordCoreNLP pipeline;

	public Pipeline() {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		pipeline = new StanfordCoreNLP(props);
	}

	public String process(String text) throws LifException
	{
		Container container = new Container();
		container.setText(text);
		return process(container);
	}

	public String process(Container container) throws LifException
	{
		CoreDocument document = new CoreDocument(container.getText());
		pipeline.annotate(document);


		// Process the sentences.
		int id = 0;
		View sentences = container.newView();
		for (CoreSentence s : document.sentences()) {
			Pair<Integer,Integer> offsets = s.charOffsets();
			sentences.newAnnotation("s-" + (id++), Uri.SENTENCE, offsets.first, offsets.second);
		}
		if (id > 0) {
			sentences.addContains(Uri.SENTENCE, this.getClass().getName(), "stanford");
		}

		// Process tokens. Include lemmas and part of speech.
		View tokens = container.newView();
		id = 0;
		for (CoreLabel token : document.tokens()) {
			int start = token.beginPosition();
			int end = token.endPosition();
			Annotation a = tokens.newAnnotation("tok-" + (id++), Uri.TOKEN, start, end);
			set(a, Features.Token.LEMMA, token.lemma());
			set(a, Features.Token.PART_OF_SPEECH, token.tag());
			set(a, Features.Token.WORD, token.word());
			set(a, "category", token.category());
		}
		if (id > 0) {
			tokens.addContains(Uri.TOKEN, this.getClass().getName(), "stanford");
		}

		View ner = container.newView();
		id = 0;
		for (CoreEntityMention mention : document.entityMentions()) {
			Pair<Integer, Integer> offset = mention.charOffsets();
			Annotation a = ner.newAnnotation("ner-" + (id++), Uri.NE, offset.first, offset.second);
			a.addFeature(Features.NamedEntity.CATEGORY, mention.entityType());
		}
		if (id > 0) {
			ner.addContains(Uri.NE, this.getClass().getName(), "stanford");
		}
		return new Data(Uri.LIF, container).asPrettyJson();
	}

	protected void set(Annotation a, String key, String value) {
		if (value == null) {
			return;
		}
		a.addFeature(key, value);
	}
}
