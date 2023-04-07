package com.tapioca.design.patterns.builder.recursive;

class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {
    protected Person person1 = new Person();

    public SELF withName(String name) {
        person1.name = name;
        return (SELF) this;
    }

    protected SELF self() {
        return (SELF) this;
    }

    public Person build() {
        return person1;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
    public EmployeeBuilder worksAt(String position) {
        person1.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class RecursiveBuilderDemo {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder();
        Person tapioca = eb.withName("Tapioca")
                .worksAt("Developer")
                .build();
        System.out.println(tapioca);
    }
}
