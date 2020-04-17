package zhh_fu.example.lock;

import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.SafeThread;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@SafeThread
public class LockExample2 {
    private final Map<String, Data> map = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final  Lock readLock = lock.readLock();
    private final  Lock writeLock = lock.writeLock();

    public static void main(String[] args) throws Exception {

    }

    private Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value){
        writeLock.lock();
        try{
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data{

    }
}
