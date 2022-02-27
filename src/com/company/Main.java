package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        int size = 1024;
        int max = 5;

        try {

            byte[] buffer = new byte[size];
            InputStream in = new FileInputStream("C:\\Users\\kahom\\IdeaProjects\\CS421 Assignment 2 Process Synchronization\\src\\com\\company\\New OpenDocument Text.txt");
            OutputStream out = new FileOutputStream("outputfile.txt");
            int c;

            //start consumer/producer

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // starting consumer thread
        //new Consumer(q, max);

        // starting producer thread
        //new Producer(q, max);
    }
}


/*
consumer opens in stream to file
consumer puts a random number of bytes from file to buffer
producer opens out stream to file
producer puts a random number of bytes from buffer to file

in file >> producer >> buffer >> consumer >> outfile
 */