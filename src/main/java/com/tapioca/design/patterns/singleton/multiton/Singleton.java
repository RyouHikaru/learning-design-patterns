package com.tapioca.design.patterns.singleton.multiton;

import java.util.HashMap;

/**
 * Multiton - allowed to make multiple instances
 * of an object, but it's essentially a kind of
 * key value store with lazy loading. It enforces
 * the amount of instance.
 */
enum Subsystem {
    PRIMARY, AUXILIARY, FALLBACK
}

class Printer {

    private static int instanceCount = 0;

    private Printer() {
        instanceCount++;
        System.out.println("A total of " + instanceCount + " instances are created so far.");
    }

    private static HashMap<Subsystem, Printer> instances = new HashMap<>();

    public static Printer get(Subsystem subsystem) {
        if (instances.containsKey(subsystem))
            return instances.get(subsystem);

        Printer instance = new Printer();
        instances.put(subsystem, instance);
        return instance;
    }

}

public class Singleton {
    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        Printer aux2 = Printer.get(Subsystem.PRIMARY);
    }
}
