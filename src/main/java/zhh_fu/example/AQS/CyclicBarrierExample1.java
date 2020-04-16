package zhh_fu.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample1 {
    private static int threadCount = 4;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            log.info("callback is running");
        }
    });

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

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        //线程需要自己执行await方法进入等待状态
        cyclicBarrier.await();
        log.info("{} is continue", threadNum);
    }
}
