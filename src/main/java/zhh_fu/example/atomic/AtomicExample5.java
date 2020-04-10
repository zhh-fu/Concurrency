package zhh_fu.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.SafeThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@SafeThread
public class AtomicExample5 {
    //private static AtomicReference<Integer> count = new AtomicReference<>(0);
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    private volatile int count = 100;
    private static AtomicExample5 example5 = new AtomicExample5();
    public static void main(String[] args) throws Exception {
        /*
        count.compareAndSet(0,2);
        log.info("count:{}",count.get());
        count.compareAndSet(0,1);
        log.info("count:{}",count.get());
        count.compareAndSet(2,4);
        log.info("count:{}",count.get());
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());
        */
        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }

        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 2,{}",example5.getCount());
        } else{
            log.info("update failed,{}",example5.getCount());
        }
    }
}
