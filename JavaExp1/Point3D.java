class Point2D {
    protected int x,y;
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point2D() {
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void offset(int a, int b){
        this.x += a;
        this.y += b;
    }
    public double getDistanceTo(Point2D p){
        return Math.sqrt((p.getX()-x)*(p.getX()-x)+(p.getY()-y)*(p.getY()-y));
    }
}
public class Point3D extends Point2D {
    private int z;
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
    public Point3D(Point2D p, int z) {
        super(p.x, p.y);
        this.z = z;
    }
    public Point3D() {
    }
    public void offset(int a, int b, int c) {
        super.offset(a, b);
        this.z += c;
    }
    public int getZ() {
        return z;
    }
    public void setZ(int z) {
        this.z = z;
    }
    public double getDistanceTo(Point3D p){
        return Math.sqrt((p.getX()-x)*(p.getX()-x)+(p.getY()-y)*(p.getY()-y)+(p.getZ()-z)*(p.getZ()-z));
    }

    public static void main(String[] args) {
        Point2D p2d1 = new Point2D(1,1);
        Point2D p2d2 = new Point2D(2,2);
        System.out.println("p2d1到p2d2的距离为"+p2d1.getDistanceTo(p2d2));
        Point3D p3d1 = new Point3D(1,1,1);
        Point3D p3d2 = new Point3D(2,2,2);
        System.out.println("p3d1到p3d2的距离为"+p3d1.getDistanceTo(p3d2));
    }
}