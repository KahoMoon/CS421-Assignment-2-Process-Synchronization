package com.company;

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
                }
            }
        });

        producer.start();

    }
}
