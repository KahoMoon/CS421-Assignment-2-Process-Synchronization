package com.company;// Consumer class
import java.util.concurrent.Semaphore; 

class Consumer implements Runnable 
{ 
	Queue q; 
	Consumer(Queue q){ 
		this.q = q; 
		new Thread(this, "Consumer").start(); 
	} 
	
	public void run() 
	{ 
		for(int i=0; i < 5; i++) 
			// consumer get items 
			q.get(); 
	} 
} 
