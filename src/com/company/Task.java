package com.company;

import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Task {

    /**
     * semaphore for accessing buffer
     */
    private final Semaphore readWrite = new Semaphore(1);

    /**
     * max random number of bytes per iteration
     */
    int n = 10;

    /**
     * intermediate for byte array between producer/consumer
     */
    ArrayDeque<Byte> buffer;

    Random random = new Random();

    /**
     * file to be copied
     */
    File inFile;

    /**
     * copy of the file
     */
    File outFile;

    /**
     * size of the file
     */
    int fileSize;

    int bufferSize;

    /**
     * input stream for file
     */
    FileInputStream fileInputStream;

    FileOutputStream fileOutputStream;


    public Task(String fileName) throws IOException {

        inFile = new File(fileName);
        fileInputStream = new FileInputStream(inFile);

        fileSize = (int) inFile.length();
        n = fileSize;
        bufferSize = fileSize;
        outFile = new File("copy" + getFileExtension(inFile));
        fileOutputStream = new FileOutputStream(outFile);

        buffer = new ArrayDeque<>();

    }

    public void producer() throws InterruptedException, IOException {
        //readWrite.acquire();

        /**
         *
         *
         *
         * move acquire and release inside the while loop
         *
         *
         *
         */

        while (fileSize > 0) {
            int randomNumber = random.nextInt(n + 1 - 1) + 1;
            byte[] b = new byte[randomNumber];

            fileInputStream.read(b);

            /*for (int i = 0; i < b.length; i++) {
                System.out.println(b[i]);
            }*/

            fileSize = fileSize - randomNumber;
            n = fileSize;

            for (int i = 0; i < b.length; i++) {
                buffer.push(b[i]);
            }

            /*int bufferSizeTemp = buffer.size();
            for (int i = 0; i < bufferSizeTemp; i++) {
                System.out.println(buffer.pop());
            }*/

            //fileOutputStream.write(b);
        }

        for (Byte item: buffer) {
            System.out.println(item);
        }

        //readWrite.release();
    }

    public void consumer() throws InterruptedException, IOException {
        readWrite.acquire();

        while (!buffer.isEmpty()) {

            for (Byte item: buffer) {
                System.out.println(item);
            }

            /*int randomNumber = random.nextInt(buffer.size() + 1 - 1) + 1;

            for (int i = 0; i < randomNumber; i++) {
                fileOutputStream.write(buffer.pop());
            }*/

        }

        readWrite.release();
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

}
