package com.tapioca.design.patterns.state.classic;

/**
 * State - a pattern in which the object's behavior is determined
 * by its state. An object transitions from one state to another
 * (something needs to trigger  a transition).

 * A formalized construct which manages state and transitions is called
 * a state machine.

 * This implementation is not done nowadays.
 */
class State {
    void on(LightSwitch ls) {
        System.out.println("Light is already on");
    }

    void off(LightSwitch ls) {
        System.out.println("Light is already off");
    }
}

class OnState extends State {
    public OnState() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching light off...");
        ls.setState(new OffState());
    }
}

class OffState extends State {
    public OffState() {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching light on...");
        ls.setState(new OnState());
    }
}

class LightSwitch {
    private State state; // OnState, OffState

    public LightSwitch() {
        state = new OffState();
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }

    public void setState(State state) {
        this.state = state;
    }
}

public class ClassicStateDemo {
    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
