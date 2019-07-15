package byog.Core;

import java.io.Serializable;

public class Pointer implements Serializable{
    private static final long serialVersionUID = -2045295948562185979L;
    public int x;
    public int y;
    public int xEnd;
    public int yEnd;
    public Pointer(int x, int y, int xEnd, int yEnd){
        this.x=x;
        this.y=y;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
    }

}
