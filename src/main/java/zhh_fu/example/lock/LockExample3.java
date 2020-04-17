package zhh_fu.example.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import zhh_fu.annotations.SafeThread;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@SafeThread
public class LockExample3 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                //调用lock()方法，进入AQS的等待队列中
                reentrantLock.lock();
                log.info("wait signal"); // 1
                //该线程从等待队列中移除。对应锁的释放，
                //并加入Condition等待队列中
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //线程1被步骤3唤醒后继续执行
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            //因为线程释放锁，线程2被唤醒，加入AQS等待队列中
            reentrantLock.lock();
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //发送信号，此时condition队列中有我们线程1的节点，
            //于是它就被取出来了，加入到AQS等待队列中，
            //此时线程1没有被唤醒
            condition.signalAll();
            log.info("send signal ~ "); // 3
            //释放锁，AQS只有线程1，于是线程1被唤醒了，继续执行
            reentrantLock.unlock();
        }).start();
    }
}
