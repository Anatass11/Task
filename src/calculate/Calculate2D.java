package calculate;

import points.Point2D;

public class Calculate2D {
    public static double calculate(Point2D a, Point2D b){
        return Math.sqrt(Math.pow((b.getX() - a.getX()),2) + Math.pow((b.getY() - a.getY()),2)); // ((x2 - x1)^2 + (y2 - y1)^2)^0.5
    }
}
