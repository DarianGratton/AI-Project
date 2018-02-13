package game;

import java.awt.geom.Ellipse2D;

public class Space extends Ellipse2D.Double {

    private int alpha;
    private int num;
    private double x;
    private double y;
    
    private Space() {   }    

    public Space(int alpha, int num, double x, double y) {
        super(x, y, 65, 65);
        this.alpha = alpha;
        this.num = num;
        this.x = x;
        this.y = y;
        
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Space [alpha=" + alpha + ", num=" + num + ", x=" + x + ", y=" + y + "]";
        
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}