package com.tapioca.design.patterns.factory;

/**
 * Factory - generate an object as whole, not piecewise
 */
class Point {
    private double x;
    private double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }

}


public class Factory {
    public static void main(String[] args) {
        Point point = Point.Factory.newCartesianPoint(3, 4);
    }
}
