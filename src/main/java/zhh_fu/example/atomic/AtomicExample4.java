package zhh_fu.example.atomic;

import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.SafeThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@SafeThread
public class AtomicExample4 {
    //请求总数
    public static int clientTotal = 2000;
    //同时执行并发的线程数
    public static int threadTotal = 200;
    //public static AtomicLong count = new AtomicLong(0);
    private static AtomicBoolean ishappened = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i=0;i<clientTotal;i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (Exception ex){
                    log.error("Exception" + ex);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("ishappened:{}" ,ishappened.get());
    }

    private static void test(){
        if (ishappened.compareAndSet(false,true)){
            log.info("execute");
        }
        //count.incrementAndGet();
        //count.getAndIncrement();
    }
}
