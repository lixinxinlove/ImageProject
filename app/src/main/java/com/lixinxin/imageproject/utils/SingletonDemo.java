package com.lixinxin.imageproject.utils;

public class SingletonDemo {

    private static SingletonDemo instance;

    private SingletonDemo() {
    }

    //第一种（懒汉，线程不安全）
    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }


    //  第二种（懒汉，线程安全）：
    public static synchronized SingletonDemo getInstance1() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }


    // 第三种（饿汉）：
    private static SingletonDemo instance2 = new SingletonDemo();

    public static SingletonDemo getInstance2() {
        return instance2;
    }


    //  第四种（饿汉，变种）：
    private static SingletonDemo instance3 = null;

    static {
        instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance3() {
        return instance3;
    }

    //   第五种（静态内部类）：
    private static class SingletonHolder {
        private static final SingletonDemo instance4 = new SingletonDemo();
    }

    public static final SingletonDemo getInsatance4() {
        return SingletonHolder.instance4;
    }


    //第六种（枚举）：
    public enum SingletonDemo6 {
        instance;
        public void whateverMethod(){
        }
    }




    //第七种（双重校验锁）：
    private volatile static SingletonDemo singletonDemo7;

    public static SingletonDemo getSingletonDemo7(){
        if (singletonDemo7 == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo7 == null) {
                    singletonDemo7 = new SingletonDemo();
                }
            }
        }
        return singletonDemo7;
    }


}
