import java.io.Serializable;

public class Point implements Serializable {
    public final float x;
    public final float y;
    public final int id;

    public Point(float x, float y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public void show(){
        System.out.println("x: " + x + ", y: " + y + ", id: " + id);
    }
}