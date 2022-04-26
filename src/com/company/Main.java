package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Task task = new Task("C:\\Users\\kahom\\IdeaProjects\\CS421 Assignment 2 Process Synchronization\\src\\com\\company\\New OpenDocument Text.txt");

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

    }
}
