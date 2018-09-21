package org.lappsgrid.services.stanford.rest;

import org.lappsgrid.serialization.LifException;
import org.lappsgrid.services.stanford.corenlp.Pipeline;

/**
 * Synchronizes access to the <code>Pipeline.process(String)</code> method.
 */
public class PipelineProxy
{
	private Pipeline pipeline = new Pipeline();

	public synchronized String process(String text) throws LifException
	{
		return pipeline.process(text);
	}
}
