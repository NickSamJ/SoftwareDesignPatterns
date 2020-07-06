package producerconsumer.testcontroller;

import producerconsumer.customqueue.CustomBlockedQueue;

public class  Consumer extends Thread{
    CustomBlockedQueue buffer;
    private boolean running;

    public Consumer(CustomBlockedQueue buffer) {
        this.buffer = buffer;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run(){
        running = true;
        while(running){
            System.out.println("Consumer " + getId() + " - Executes " + buffer.take());
        }
        String tempMessages;
        while((tempMessages = buffer.get()) != null){
            System.out.println("Consumer " + getId() + " - Executes " + tempMessages);
        }
    }

}
