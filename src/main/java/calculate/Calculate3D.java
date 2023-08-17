package calculate;

import points.Point2D;
import points.Point3D;

public class Calculate3D extends Calculate2D{

    public static double calculate(Point3D a, Point3D b) {
        return (Math.sqrt(Math.pow(calculate(new Point2D(a.getX(), a.getY()), new Point2D(b.getX(), b.getY())), 2) // ((x2 - x1)^2 + (y2 - y1)^2) +
                + Math.pow((b.getZ() - a.getZ()), 2))); // + (z2 - z1)^2))^0.5
    }
}
