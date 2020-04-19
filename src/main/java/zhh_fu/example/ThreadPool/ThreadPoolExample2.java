package zhh_fu.example.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        //可调度的线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        /*
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("scheduled run");
            }
        },3,TimeUnit.MILLISECONDS);
        */

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("scheduled run");
            }
        },1,3,TimeUnit.SECONDS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5*1000);
        //executorService.shutdown();
    }
}
