package com.tapioca.design.patterns.flyweight.user;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Flyweight - a space optimization technique that lets us use
 * less memory by storing externally the data associated with
 * similar objects.
 */
class User {
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2 {
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {
        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1) return idx;
            else {
                strings.add(s);
                return strings.size() - 1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(s -> getOrAdd.apply(s)).toArray();
    }

    public String getFullName() {
        return Arrays.stream(names).mapToObj(i -> strings.get(i))
                .collect(Collectors.joining(","));
    }
}

public class Flyweight {
    public static void main(String[] args) {
        // Instead of doing this
        User user = new User("John Smith");
        User user1 = new User("Jane Smith");

        // This is much more efficient
        User2 user2 = new User2("John Smith");
        User2 user3 = new User2("Jane Smith");
    }

}
