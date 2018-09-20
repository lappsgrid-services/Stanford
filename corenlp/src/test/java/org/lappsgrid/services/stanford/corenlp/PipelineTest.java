package org.lappsgrid.services.stanford.corenlp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lappsgrid.serialization.LifException;

/**
 *
 */
public class PipelineTest
{
	public static final String s1 = "Karen flew to New York.";
	public static final String s2 = "Nancy flew to Bloomington.";

	protected Pipeline pipeline;

	@Before
	public void setup() {
		pipeline = new Pipeline();
	}

	@After
	public void teardown() {
		pipeline = null;
	}

	@Test
	public void s1() throws LifException
	{
		System.out.println(pipeline.process(s1));
	}

	@Test
	public void s2() throws LifException
	{
		System.out.println(pipeline.process(s2));
	}

	@Test
	public void both() throws LifException
	{
		System.out.println(pipeline.process(s1));
		System.out.println(pipeline.process(s2));
	}
}
