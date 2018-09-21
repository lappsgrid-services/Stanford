package org.lappsgrid.services.stanford.rest;

import org.lappsgrid.serialization.LifException;
import org.lappsgrid.services.stanford.corenlp.Pipeline;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.*;

/**
 *
 */
@RestController
public class CoreNLP
{
	private static final Logger logger = LoggerFactory.getLogger(CoreNLP.class);

	private static PipelineProxy pipeline = new PipelineProxy();

	@PostMapping(path = "/api", consumes = "text/plain", produces = "application/json")
	public String processText(@RequestBody String text) throws LifException
	{
		try
		{
			logger.info("Processing text. Size: {}", text.length());
			return pipeline.process(text);
		}
		catch (LifException e) {
			logger.warn("Error processing request: " + e.getMessage());
			throw e;
		}
	}

}
