package com.tapioca.design.patterns;

/**
 * Liskov Substitution Principle (LSP)
 */
class Rectangle {
    protected int width;
    protected int height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    /*
    You can have a checker that will distinguish the base class from the derived clcass
     */
    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

/*
LSP states that you should be able to substitute a derived class for a base class.
This derived class violates it.
 */
class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

/*
A plausible solution is to create something that will generate a class based
on a boolean critera (isSquare in this case).
 */
class RectangleFactory {
    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquarr(int side) {
        return new Rectangle(side, side);
    }
}

public class LSP {
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        // area = width * 10
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        // Works fine
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        // But this, ugh
        Rectangle sq = new Square();
        sq.setWidth(5);
        useIt(sq);
    }
}
