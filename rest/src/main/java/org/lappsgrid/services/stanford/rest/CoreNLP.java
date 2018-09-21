package org.lappsgrid.services.stanford.rest;

import org.lappsgrid.serialization.LifException;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.services.stanford.corenlp.Pipeline;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
@RestController()
public class CoreNLP
{
	private static final Logger logger = LoggerFactory.getLogger(CoreNLP.class);

	private static PipelineProxy pipeline = new PipelineProxy();
	private static AtomicLong count = new AtomicLong();

	@PostMapping(path = "/api", consumes = "text/plain", produces = "application/json")
	public String processText(@RequestBody String text) throws LifException
	{
		try
		{
			long transaction = count.incrementAndGet();
			logger.info("{}. Processing text. Size: {}", transaction, text.length());
			float start = System.nanoTime();
			String result = pipeline.process(text);
			logger.info("{}. Time: {}", transaction, ((System.nanoTime()-start)/1e6));
			return result;
		}
		catch (LifException e) {
			logger.warn("Error processing request: " + e.getMessage());
			throw e;
		}
	}

	@GetMapping(path="/version", produces = "application/json")
	public String version() {
		Map<String,String> data = new HashMap<String, String>();
		data.put("version", Version.getVersion());
		return Serializer.toPrettyJson(data);
	}

	@GetMapping(path="/count", produces="application/json")
	public String count() {
		Map<String,String> data = new HashMap<String, String>();
		data.put("count", Long.toString(count.get()));
		return Serializer.toPrettyJson(data);
	}



}
