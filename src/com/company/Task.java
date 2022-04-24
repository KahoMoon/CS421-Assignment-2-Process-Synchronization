package com.company;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Task {

    /**
     * semaphore for accessing buffer
     */
    private Semaphore readWrite = new Semaphore(1);

    /**
     * buffer for bytes between the producer and consumer
     */
    private ArrayDeque<Byte> buffer = new ArrayDeque<>();

    /**
     * max random number of bytes per iteration
     */
    int n = 10;

    Random random = new Random();

    /**
     * file to be copied
     */
    File file;

    int fileLength;

    /**
     * input stream for file
     */
    FileInputStream fileInputStream;

    FileOutputStream fileOutputStream;

    /**
     * array holding file data as bytes
     */
    ArrayDeque<Integer> bFileArr;

    byte[] bFile;

    public Task(String fileName) throws IOException {
        bFileArr = new ArrayDeque<>();
        fileInputStream = new FileInputStream(fileName);
        fileOutputStream = new FileOutputStream("copy.txt");

        int b;

        while ((b = fileInputStream.read()) != -1) {
            bFileArr.add(b);


        }

        int arrSize = bFileArr.size();
        for (int i = 0; i < arrSize; i++) {
            int t = bFileArr.removeFirst();
            fileOutputStream.write(t);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    public void producer() throws InterruptedException {

    }

    public void consumer() {

    }

}
