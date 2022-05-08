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
        if (n > fileSize || n <= 1) {
            n = fileSize;
        }
        bufferSize = fileSize;
        outFile = new File("copy" + getFileExtension(inFile));
        fileOutputStream = new FileOutputStream(outFile);

        buffer = new ArrayDeque<>();

    }

    public void producer() throws InterruptedException, IOException {

        while (fileSize > 0) {
            readWrite.acquire();
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
            //System.out.println("asdfSADF" + buffer.size());
            readWrite.release();
        }

        /*for (Byte item: buffer) {
            System.out.println(item);
        }*/

    }

    public void consumer() throws InterruptedException, IOException {

        while (bufferSize > 0) {
            readWrite.acquire();

            //generate random number and create byte array with that random size
            int randomNumber = random.nextInt(bufferSize + 1 - 1) + 1;
            byte[] b = new byte[randomNumber];

            for (int i = 0 ; i < randomNumber; i++) {
                try {
                    b[i] = buffer.pollLast();
                } catch (Exception e){

                }
                //System.out.println(b[i]);
            }

            bufferSize = bufferSize - randomNumber;

            fileOutputStream.write(b);

            readWrite.release();
        }

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
