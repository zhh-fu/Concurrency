package zhh_fu.example.singleton;

import zhh_fu.annotations.UnSafeThread;

/**
 * 懒汉模式
 * 单例的使用在第一次创建的时候运行
 */
@UnSafeThread
public class Singleton1 {
    private static Singleton1 instance = null;
    private Singleton1(){

    }

    //静态的工厂方法
    public static Singleton1 getInstance(){
        if (instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
