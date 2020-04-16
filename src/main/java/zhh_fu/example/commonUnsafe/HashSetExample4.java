package zhh_fu.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.UnSafeThread;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@UnSafeThread
public class HashSetExample4 {
    //请求总数
    public static int clientTotal = 5000;
    //同时执行并发的线程数
    public static int threadTotal = 200;
    private static Set<Integer> set = new HashSet<>();


    public static void main(String[] args) throws Exception {
        Map<Integer,Integer> map = new HashMap<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (Exception ex){
                    log.error("Exception" + ex);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}" ,set.size());
    }

    private static void add(int i){
        set.add(i);
    }
}
