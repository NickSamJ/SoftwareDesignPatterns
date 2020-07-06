package producerconsumer.testcontroller;

import producerconsumer.customqueue.CustomBlockedQueue;

public class Controller {
    public static void main(String[] args) throws InterruptedException {
        CustomBlockedQueue buffer = new CustomBlockedQueue(10);
        Producer producer = new Producer(buffer);
        Producer producer1 = new Producer(buffer);
        Producer producer2 = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        Consumer consumer1 = new Consumer(buffer);

        producer.start();
        producer1.start();
        producer2.start();
        consumer.start();
        consumer1.start();
        try {
            producer.join();
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer.setRunning(false);
        consumer1.setRunning(false);
        Thread.sleep(1000);
        System.out.println("Main is ready");
    }
}
