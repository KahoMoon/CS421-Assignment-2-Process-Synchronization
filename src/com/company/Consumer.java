package com.company;// Consumer class
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class Consumer implements Runnable
{
	Queue q;
	Consumer(Queue q, OutputStream out){
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