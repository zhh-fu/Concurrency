package zhh_fu.example.singleton;

import zhh_fu.annotations.NotRecommend;
import zhh_fu.annotations.SafeThread;

/**
 * 懒汉模式
 * 单例实例在类装载使用的时候进行创建
 */
@SafeThread
@NotRecommend
public class Singleton3 {

    private static Singleton3 instance = null;

    private Singleton3(){

    }

    //静态的工厂方法
    public static synchronized Singleton3 getInstance(){
        if (instance == null){
            instance = new Singleton3();
        }
        return instance;
    }
}
