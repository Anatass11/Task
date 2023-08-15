package points;

public class Point3D extends Point2D{
    private double z;

    public Point3D(double x, double y, double z){
        super(x, y);
        this.z = z;
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString(){
        return "(" + super.getX() + ", " + super.getY() + ", " + z + ")";
    }
}
