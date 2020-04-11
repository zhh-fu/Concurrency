package zhh_fu.example.singleton;

import zhh_fu.annotations.NotRecommend;
import zhh_fu.annotations.SafeThread;
import zhh_fu.annotations.UnSafeThread;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * 线程不安全
 */
@UnSafeThread
public class Singleton4 {

    private static Singleton4 instance = null;

    private Singleton4(){

    }

    //1、 memory = allocate() 分配对象的内存空间
    //2、 ctorInstance() 初始化对象
    //3、 instance = memory 设置instance指向刚分配的内存
    //静态的工厂方法
    public static Singleton4 getInstance(){
        if (instance == null){ //双重检测机制
            synchronized (Singleton4.class){ //同步锁
                if (instance == null){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
