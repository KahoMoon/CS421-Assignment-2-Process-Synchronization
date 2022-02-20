package com.company;

import java.util.concurrent.Semaphore;

// Producer class 
class Producer implements Runnable 
{ 
	Queue q; 
	Producer(Queue q) { 
		this.q = q; 
		new Thread(this, "Producer").start(); 
	} 
	
	public void run() { 
		for(int i=0; i < 5; i++) 
			// producer put items 
			q.put(i); 
	} 
} 

