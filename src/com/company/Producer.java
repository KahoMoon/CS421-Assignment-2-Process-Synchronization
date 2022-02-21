package com.company;

import java.io.FileInputStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

// Producer class 
class Producer implements Runnable 
{ 
	Queue q;
	int randomNum;

	Producer(Queue q, int max) {
		this.q = q; 
		new Thread(this, "Producer").start();
	} 
	
	public void run() { 
		for(int i=0; i < randomNum; i++)
			// producer put items 
			q.put(i); 
	} 
} 

