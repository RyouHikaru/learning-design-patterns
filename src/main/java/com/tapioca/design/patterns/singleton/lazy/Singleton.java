package com.tapioca.design.patterns.singleton.lazy;

/**
 * Lazy Singleton - lazy loading, making it thread-safe
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Initializing a lazy singleton");
    }

    /* To make it thread-safe, make it synchronized
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
     */

    // Double-checked locking (already outdated)
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}

public class Singleton {
    public static void main(String[] args) {
        LazySingleton singleton = LazySingleton.getInstance();
        System.out.println(singleton);
    }
}
