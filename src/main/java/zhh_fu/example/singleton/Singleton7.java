package zhh_fu.example.singleton;

import zhh_fu.annotations.Recommend;
import zhh_fu.annotations.SafeThread;

/**
 * 枚举模式，线程最安全
 */
@SafeThread
@Recommend
public class Singleton7 {

    private static volatile Singleton7 instance = null;

    private Singleton7(){

    }

    private enum Singleton {
        INSTANCE;

        private Singleton7 singleton7;

        //JVM保证方法只被调用一次
        Singleton(){
            singleton7 = new Singleton7();
        }

        public Singleton7 getSingleton(){
            return singleton7;
        }
    }


    public static Singleton7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }
}
