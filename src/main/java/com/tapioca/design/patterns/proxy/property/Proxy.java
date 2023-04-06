package com.tapioca.design.patterns.proxy.property;

/**
 * Property Proxy - the idea of this design pattern is to
 * simply replace every single filed that you store with a
 * specialized kind of construct, which allows more control.
 */
class Property<T> {
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        // Do some logging here
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property<?> property = (Property<?>) o;

        return value != null ? value.equals(property.value) : property.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

class Creature {
    private Property<Integer> agility = new Property<>(10);

    public void setAgility(Integer value) {
        // We cannot do agility = value, sadly
        agility.setValue(value);
    }

    public Integer getAgility() {
        return agility.getValue();
    }
}

public class Proxy {
    public static void main(String[] args) {
        Creature c = new Creature();
        c.setAgility(12);
    }
}
