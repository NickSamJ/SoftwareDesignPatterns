package producerconsumer.customqueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockedQueue {
    private final int capacity;
    private final LinkedList<String> baseQueue;
    private final Lock lock = new ReentrantLock();
    private final Condition isFull = lock.newCondition();
    private final Condition isEmpty = lock.newCondition();

    public CustomBlockedQueue(int capacity) {
        this.capacity = capacity;
        baseQueue = new LinkedList<>();
    }

    public CustomBlockedQueue() {
        this(16);
    }

    public String get(){
        String res = null;
        if(baseQueue.size() != 0){
            res = baseQueue.remove(baseQueue.size()-1);
        }
        return res;
    }

    public void put(String element){
        lock.lock();
        try{
            while(baseQueue.size() >= capacity){
                isFull.await();
            }
            baseQueue.add(element);
            isEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String take(){
        lock.lock();
        String res = null;
        try{
            while(baseQueue.size() == 0){
                isEmpty.await();
            }
            res = baseQueue.remove(baseQueue.size()-1);
            isFull.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return res;
    }
}
