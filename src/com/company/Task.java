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

    byte[] bFile;

    public Task(String fileName) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fileName, "r");
        bFile = new byte[(int)f.length()];
        f.readFully(bFile);

        /*try (FileOutputStream fos = new FileOutputStream("copy.txt")) {
            fos.write(bFile);
        }*/

        for (int i = 0; i < f.length(); i++) {
            buffer.add(bFile[i]);
        }

        fileOutputStream = new FileOutputStream("copy.txt");
        for (int i = 0; i < bFile.length; i++) {

        }
    }

    public void producer() throws InterruptedException {
        readWrite.acquire();

        readWrite.release();
    }

    public void consumer() throws InterruptedException {
        readWrite.acquire();

        readWrite.release();
    }

}
