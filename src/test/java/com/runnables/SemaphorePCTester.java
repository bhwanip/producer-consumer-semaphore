package com.runnables;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.custom.CustomSemaphore;
import com.semaphore.runnables.Consumer;
import com.semaphore.runnables.Producer;

public class SemaphorePCTester {
	
	public static final Logger logger = LoggerFactory.getLogger(SemaphorePCTester.class);

	@Test
	public void test() throws InterruptedException {
		logger.info("Observer logs to see example working!");
		LinkedList<String> sharedList = new LinkedList<String>();
		CustomSemaphore semaphore = new CustomSemaphore(1);
		new Thread(new Producer(sharedList, semaphore)){
			{setName("Producer");}
		}.start();
		new Thread(new Consumer(sharedList, semaphore)){
			{setName("Consumer");}
		}.start();
		Thread.currentThread().join(30*1000);
		assertTrue(true);
	}
}
