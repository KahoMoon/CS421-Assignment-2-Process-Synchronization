package com.company;

import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;
import com.company.CircularQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Queue
{ 
	// an item 
	int item;

	FileInputStream in = null;
	FileOutputStream out = null;

	Queue(int size){
		CircularQueue buffer = new CircularQueue(size);

		try {/*from w  ww.  ja v  a2  s.c  o m*/
			in = new FileInputStream("xanadu.txt");
			out = new FileOutputStream("outagain.txt");
			int c;

			/*while ((c = in.read()) != -1) {
				out.write(c);
			}*/

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	// semCon initialized with 0 permits 
	// to ensure put() executes first 
	static Semaphore semCon = new Semaphore(0); 
	static Semaphore semProd = new Semaphore(1); 
	
	// to get an item from buffer 
	void get() 
	{ 
		try { 
			// Before consumer can consume an item, 
			// it must acquire a permit from semCon 
			semCon.acquire(); 
		} 
		catch(InterruptedException e) { 
			System.out.println("InterruptedException caught"); 
		} 
		
		// consumer consuming an item 
		System.out.println("Consumer consumed item : " + item); 
		
		// After consumer consumes the item, 
		// it releases semProd to notify producer 
		semProd.release(); 
	} 
	
	// to put an item in buffer 
	void put(int item) 
	{ 
		try { 
			// Before producer can produce an item, 
			// it must acquire a permit from semProd 
			semProd.acquire(); 
		} catch(InterruptedException e) { 
			System.out.println("InterruptedException caught"); 
		} 
		
		// producer producing an item 
		this.item = item; 
		
		System.out.println("Producer produced item : " + item); 
		
		// After producer produces the item, 
		// it releases semCon to notify consumer 
		semCon.release(); 
	}

	void close(){
		if (in != null) {
			in.close();
		}
		if (out != null) {
			out.close();
		}
	}
} 
