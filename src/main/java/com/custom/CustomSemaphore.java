package com.custom;

public class CustomSemaphore {

	private int permits;

	public CustomSemaphore(int permits) {
		super();
		this.permits = permits;
	}

	public synchronized void acquire() throws InterruptedException{
		while(this.permits == 0){
			this.wait();
		}
		this.permits--;
	}

	public synchronized void release() {
		this.permits++;
		if(this.permits > 0)
			this.notifyAll();
	}

}
