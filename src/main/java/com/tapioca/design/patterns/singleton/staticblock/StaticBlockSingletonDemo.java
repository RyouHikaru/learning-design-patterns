package com.tapioca.design.patterns.singleton.staticblock;

import java.io.File;
import java.io.IOException;

/**
 * Static Block Singleton - will always instantiate an instance
 */
class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.err.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}

public class StaticBlockSingletonDemo {
    public static void main(String[] args) throws Exception {
        StaticBlockSingleton singleton = StaticBlockSingleton.getInstance();
        System.out.println(singleton);
    }
}
