package zhh_fu.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {
    private static int threadCount = 20;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception ex){
                    log.info("exception",ex);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException, TimeoutException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        //线程需要自己执行await方法进入等待状态
        try{
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException ex){
            log.warn("BrokenBarrierException | TimeoutException", ex);
        }

        log.info("{} is continue", threadNum);
    }
}
