package view;

import controller.ThreadsCoocked;

import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for(int x = 0; x<10; x++){
            ThreadsCoocked threadsCoocked = new ThreadsCoocked(semaphore);
            threadsCoocked.start();
    }
    }
}