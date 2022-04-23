package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    /**
     * array holding file data as bytes
     */
    ArrayDeque<Byte> bFileArr;

    byte[] bFile;

    public Task(String fileName) throws IOException {

    }

    public void producer() throws InterruptedException {

    }

    public void consumer() {

    }

}
