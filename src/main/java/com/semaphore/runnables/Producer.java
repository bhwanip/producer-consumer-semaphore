package com.semaphore.runnables;

import java.util.Arrays;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.custom.CustomSemaphore;

public class Producer implements Runnable {

	public static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private LinkedList<String> list;
	private CustomSemaphore semaphore;

	public Producer(LinkedList<String> list, CustomSemaphore semaphore) {
		super();
		this.list = list;
		this.semaphore = semaphore;
	}

	public void run() {
		while(true){
			try {
				semaphore.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			for (String str : Arrays.asList("ONE","TWO")) {
				produce(str);
			}
			semaphore.release();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void produce(String element) {
		list.addLast(element);
		logger.info("Produced: " +element);
	}

}
