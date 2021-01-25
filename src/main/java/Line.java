import java.io.Serializable;

public class Line implements Serializable {
    public final Point[] points;
    public final boolean someFlag;

    public Line(Point[] points, boolean someFlag) {
        this.points = points;
        this.someFlag = someFlag;
    }

    public void show(){
        for (Point point: points) {
            point.show();
        }
        System.out.println("someFlag: " + someFlag);
    }
}