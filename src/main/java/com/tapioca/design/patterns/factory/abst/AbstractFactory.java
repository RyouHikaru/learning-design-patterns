package com.tapioca.design.patterns.factory.abst;

import java.util.HashMap;
import java.util.Map;

interface HotDrink {
    void consume();
}

class Tea implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This tea is delicious");
    }
}

class Coffee implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This coffee is delicious");
    }
}

interface HotDrinkFactory {
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(int amount) {
        System.out.println("Put tea bag, boil water, pour " + amount + "mL, add lemon, enjoy!");
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(int amount) {
        System.out.println("Grind some beans, boil water, pour " + amount + "mL, add cream and sugar, enjoy!");
        return new Coffee();
    }
}

class HotDrinkMachine {

    public enum AvailableDrink {
        COFFEE, TEA
    }

    private Map<AvailableDrink, HotDrinkFactory> factories = new HashMap<>();

    public HotDrinkMachine() throws Exception {
        for (AvailableDrink drink : AvailableDrink.values()) {
            String s = drink.toString();
            String factoryName = "" + Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
            Class<?> factory = Class.forName("com.tapioca.design.patterns.factory.abst." + factoryName + "Factory");
            factories.put(drink, (HotDrinkFactory) factory.getDeclaredConstructor().newInstance());
        }
    }

    public HotDrink makeDrink(AvailableDrink drink, int amount) {
        return (factories.get(drink)).prepare(amount);
    }
}

public class AbstractFactory {
    public static void main(String[] args) throws Exception {
        HotDrinkMachine machine = new HotDrinkMachine();
        HotDrink tea = machine.makeDrink(HotDrinkMachine.AvailableDrink.TEA, 200);
        tea.consume();
    }
}
