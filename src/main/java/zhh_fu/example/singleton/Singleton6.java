package zhh_fu.example.singleton;

import zhh_fu.annotations.SafeThread;

/**
 * 静态方法块 ，静态域
 * 单例实例在类装载的时候进行创建
 */
public class Singleton6 {

    private Singleton6(){

    }
    private static Singleton6 instance = null;

    static {
        instance = new Singleton6();
    }
    //静态的工厂方法

    public static Singleton6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
