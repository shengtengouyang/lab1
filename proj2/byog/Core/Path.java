package byog.Core;

import java.io.Serializable;

public class Path implements Serializable{

    private static final long serialVersionUID = -1430774442512459195L;

    enum Shape{
        RECTANGLE, HORIZONTAL, VERTICAL
    }
    public Pointer pointer;
    private Shape shape;

    public Path(int xp, int yp, int xlength, int ylength) {
        pointer =new Pointer(xp,yp,xp+xlength-1,yp+ylength-1);
        shape=Shape.RECTANGLE;
    }

    public void setShape(Shape shape){
        this.shape=shape;
    }
    public Shape getShape(){
        return shape;
    }
}
