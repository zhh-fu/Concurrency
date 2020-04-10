package zhh_fu.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {
    //代码块
    public void test1(int j){
        //this 作用于当前对象
        synchronized (this){
            for (int i = 0;i < 20;i++){
                log.info("test 1-{} {}",j,i);
            }
        }
    }

    //方法
    public synchronized void test2(int j){
        log.info(Thread.currentThread().getName());
        for (int i = 0;i < 10;i++){
            log.info("test 1-{} {}",j,i);
        }
    }

    //方法
    public static synchronized void test3(int j){
        log.info(Thread.currentThread().getName());
        for (int i = 0;i < 10;i++){
            log.info("test 3-{} {}",j,i);
        }
    }

    public void test4(int j){
        //SynchronizedExample1.class 类锁
        synchronized (SynchronizedExample1.class){
            for (int i = 0;i < 20;i++){
                log.info("test 1-{} {}",j,i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 sync1 = new SynchronizedExample1();
        SynchronizedExample1 sync2 = new SynchronizedExample1();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            sync1.test1(1);
        });

        executorService.execute(() -> {
            sync2.test1(2);
        });

        SynchronizedExample1.test3(3);
    }
}
