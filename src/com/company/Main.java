package com.company;

public class Main {

    public static void main(String[] args) {
        // creating buffer queue

        int size = 1024;
        Queue q = new Queue(size);

        // starting consumer thread
        new Consumer(q);

        // starting producer thread
        new Producer(q);
    }
}
