package com.company;// Consumer class
import java.io.FileOutputStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class Consumer implements Runnable 
{ 
	Queue q;
	int randomNum;

	Consumer(Queue q, int max){

		this.q = q; 
		new Thread(this, "Consumer").start();
		randomNum = ThreadLocalRandom.current().nextInt(0, max + 1);

	}
	
	public void run() 
	{ 
		for(int i=0; i < randomNum; i++)
			// consumer get items 
			q.get(); 
	} 
} 
