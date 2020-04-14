package zhh_fu.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.UnSafeThread;

import java.util.concurrent.*;

@Slf4j
@UnSafeThread
public class StringExample2 {
    //请求总数
    public static int clientTotal = 5000;
    //同时执行并发的线程数
    public static int threadTotal = 200;
    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executorService =
                new ThreadPoolExecutor(150,200,60,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(6),new ThreadPoolExecutor.CallerRunsPolicy());
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i=0;i<clientTotal;i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception ex){
                    log.error("Exception" + ex);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}" ,stringBuffer.length());
    }

    private static void add(){
        stringBuffer.append("1");
    }
}
