package com.semaphore.runnables;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.custom.CustomSemaphore;

public class Consumer implements Runnable {

	public static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	private LinkedList<String> list;
	private CustomSemaphore semaphore;

	public Consumer(LinkedList<String> list, CustomSemaphore semaphore) {
		super();
		this.list = list;
		this.semaphore = semaphore;
	}

	public void run() {
		while(true){
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!list.isEmpty())
				logger.info("Consumed: " + list.removeLast());
			semaphore.release();
			if(list.isEmpty()){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
