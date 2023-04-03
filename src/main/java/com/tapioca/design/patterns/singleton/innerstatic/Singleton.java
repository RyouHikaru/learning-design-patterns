package com.tapioca.design.patterns.singleton.innerstatic;

/**
 * Inner Static Singleton - avoids the problem of synchronization
 */
class InnerStaticSingleton {
    private InnerStaticSingleton() {}

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}
public class Singleton {
    public static void main(String[] args) {

    }
}
