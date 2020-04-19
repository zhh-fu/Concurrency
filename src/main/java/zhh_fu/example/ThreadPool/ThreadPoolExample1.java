package zhh_fu.example.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {
        //单线程处理
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //多线程，无上限
        //ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newFixedThreadPool();

        for (int i=0;i<10;i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task :{}", index);
                }
            });
        }

        executorService.shutdown();
    }
}
