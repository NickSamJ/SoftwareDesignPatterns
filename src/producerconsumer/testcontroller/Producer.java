package producerconsumer.testcontroller;

import producerconsumer.customqueue.CustomBlockedQueue;

public class Producer extends Thread {
    CustomBlockedQueue buffer;

    public Producer(CustomBlockedQueue buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            buffer.put("My name is " + getId());
        }
    }
}
