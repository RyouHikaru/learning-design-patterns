package com.tapioca.design.patterns.mediator.chatroom;

import java.util.ArrayList;
import java.util.List;

/**
 * Chat Room - a simple implementation of a chat room.
 * The key idea is that everyone has a reference to the mediator
 * but at no time does a person in any way reference directly
 * another person.
 */
class Person {
    public String name;
    public ChatRoom room;
    private List<String> chatLog = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLog.add(s);
    }

    public void say(String message) {
        room.broadcast(name, message);
    }

    public void privateMessage(String who, String message) {
        room.message(name, who, message);
    }
}

// This class is the Mediator
class ChatRoom {
    private List<Person> people = new ArrayList<>();

    public void broadcast(String source, String message) {
        for (Person person : people)
            if (!person.name.equals(source))
                person.receive(source, message);
    }

    public void join(Person p) {
        String joinMsg = p.name + " joins the chat";
        broadcast("room", joinMsg);

        p.room = this;
        people.add(p);
    }

    public void message(String source, String destination, String message) {
        people.stream()
                .filter(p -> p.name.equals(destination))
                .findFirst()
                .ifPresent(person -> person.receive(source, message));
    }
}

public class ChatRoomDemo {
    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();

        Person john = new Person("John");
        Person jane = new Person("Jane");

        room.join(john); // No message here
        room.join(jane);

        john.say("Hi folks!");
        jane.say("Oh, hey john");

        Person simon = new Person("Simon");
        room.join(simon);
        simon.say("Hi everyone!");

        jane.privateMessage("Simon", "Glad you could join us!");
    }
}