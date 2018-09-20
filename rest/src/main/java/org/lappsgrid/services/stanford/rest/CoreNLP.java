package org.lappsgrid.services.stanford.rest;

import org.lappsgrid.serialization.LifException;
import org.lappsgrid.services.stanford.corenlp.Pipeline;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class CoreNLP
{
	private Pipeline pipeline;

	public CoreNLP()
	{
		pipeline = new Pipeline();
	}

	@PostMapping(path = "/api", consumes = "text/plain", produces = "application/json")
	public String processText(@RequestBody String text) throws LifException
	{
		return pipeline.process(text);
	}

}
