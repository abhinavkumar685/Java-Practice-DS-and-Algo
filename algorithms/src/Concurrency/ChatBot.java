package Concurrency;

import java.lang.*;

class Chat {
    public boolean flag = false;

    public void question(String msg) {
        synchronized(this) {
            while(flag) {
                try {
                    this.wait();
                    System.out.println("Waiting for answer.....");
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Question: " + msg);
            flag = true;
            this.notify();
        }
    }


    public void answer(String msg) {
        synchronized(this) {
            while(!flag) {
                try {
                    this.wait();
                    System.out.println("Waiting for question.....");
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Answer: " + msg);
            flag = false;
            this.notify();
        }
    }
}

class Question implements Runnable {
    String[] question = {"Hi", "How r u?", "great"};
    Chat m;

    Question(Chat m) {
        this.m = m;
        Thread t1 = new Thread(this, "Question Thread");
        t1.start();
    }

    public void run() {
        for(String q : question) {
            m.question(q);
        }
    }
}

class Answer implements Runnable {
    String[] answer = {"Hello", "I am good", "thanks"};
    Chat m;

    Answer(Chat m) {
        this.m = m;
        Thread t2 = new Thread(this, "Answer Thread");
        t2.start();
    }

    public void run() {
        for(String a : answer) {
            m.answer(a);
        }
    }
}

public class ChatBot {
    public static void main(String[] args) {
        Chat c = new Chat();
        new Question(c);
        new Answer(c);
    }
}