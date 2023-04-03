package com.tapioca.design.patterns.singleton.basic;

import java.io.*;

/**
 * Basic Singleton
 */
class BasicSingleton implements Serializable {
    private BasicSingleton() {

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // This will resolve the Serialization problem
    @Serial
    protected Object readResolve() {
        return INSTANCE;
    }
}

public class Singleton {
    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        // Problems include
        // 1. Reflection
        // 2. Serialization
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(111);

        String filename = "out/singleton.bin";
        saveToFile(singleton, filename);

        singleton.setValue(222);

        BasicSingleton singleton1 = readFromFile(filename);

        // This will result to a Non-singleton behavior
        System.out.println(singleton == singleton1);
        System.out.println(singleton.getValue());
        System.out.println(singleton1.getValue());
    }
}
