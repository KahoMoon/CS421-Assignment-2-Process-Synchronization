package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        /*
        change file path
         */
        String filePath = "C:\\Users\\kahom\\IdeaProjects\\CS421 Assignment 2 Process Synchronization\\src\\com\\company\\New OpenDocument Text.txt";












        Task task = new Task(filePath);

        Thread producer = new Thread(() -> {
            try {
                task.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                task.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

    }
}
