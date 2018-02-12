package abalone;
public class Space {

    private int alpha;

    private int num;

    private int x;

    private int y;

    

    private Space() {   }

    

    public Space(int alpha, int num, int x, int y) {

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

    public int getX() {

        return x;

    }

    @Override

    public String toString() {

        return "Space [alpha=" + alpha + ", num=" + num + ", x=" + x + ", y=" + y + "]";

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

}