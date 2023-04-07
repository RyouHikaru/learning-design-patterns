package com.tapioca.design.patterns.visitor.intrusive;

/**
 * Visitor - a pattern where a component (visitor) is allowed
 * to traverse the entire inheritance hierarchy. Implemented by
 * propagating a single visit() method throughout the entire hierarchy.

 * Intrusive Visitor - jump into classes that are already
 * written and tested, and perhaps modify the entire hierarchy.

 * Note: The problem with this is it violates the OCP.
 */
abstract class Expression {
    public abstract void print(StringBuilder sb);
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void print(StringBuilder sb) {
        sb.append(value);
    }
}

class AdditionExpression extends Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(StringBuilder sb) {
        sb.append("(");
        left.print(sb);
        sb.append("+");
        right.print(sb);
        sb.append(")");
    }
}

public class IntrusiveVisitorDemo {
    public static void main(String[] args) {
        // 1+(2+3)
        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                ));
        StringBuilder sb = new StringBuilder();
        e.print(sb);
        System.out.println(sb);
    }
}
