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
        file = new File(fileName);
        fileInputStream = new FileInputStream(file);
        fileLength = (int) file.length();
        bFile = new byte[fileLength];

        fileInputStream.read(bFile);
        fileInputStream.close();

        bFileArr = new ArrayDeque<>();
        for (int i = 0; i < bFile.length; i++) {
            bFileArr.add(bFile[i]);
        }
    }

    public void producer() throws InterruptedException {
        ArrayDeque<Byte> copy = new ArrayDeque<>(bFileArr);
        while (!bFileArr.isEmpty()) {
            readWrite.acquire();

            int randomNumber = random.nextInt(n + 1 - 1) + 1;
            for (int i = 0; i < 2; i++) {
                try {
                    byte removedByte = bFileArr.remove();
                    buffer.add(removedByte);
                } catch (Exception e) {

                }
            }

            readWrite.release();
        }
        /*System.out.println(buffer.toString().compareTo(copy.toString()));
        System.out.println(copy + "\n\n");
        System.out.println(buffer);*/
    }

    public void consumer() {

    }

}
