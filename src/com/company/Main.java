package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        // creating buffer queue

        int size = 1024;
        int max = 5;

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Queue q = new Queue(size);

        // starting consumer thread
        new Consumer(q, max);

        // starting producer thread
        new Producer(q, max);
    }
}


/*
consumer opens in stream to file
consumer puts a random number of bytes from file to buffer
producer opens out stream to file
producer puts a random number of bytes from buffer to file

in file >> producer >> buffer >> consumer >> outfile
 */