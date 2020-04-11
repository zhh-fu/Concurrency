package zhh_fu.example.singleton;

import zhh_fu.annotations.SafeThread;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@SafeThread
public class Singleton5 {

    private static volatile Singleton5 instance = null;

    private Singleton5(){

    }

    //静态的工厂方法
    //单例对象 volatile + 双重检测机制 -> 禁止指令重排
    public static Singleton5 getInstance(){
        if (instance == null){ //双重检测机制
            synchronized (Singleton5.class){ //同步锁
                if (instance == null){
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
