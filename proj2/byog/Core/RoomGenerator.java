package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;
import java.util.Random;

public class RoomGenerator implements Serializable {
    private static final long serialVersionUID = -908139635040212537L;
    private Path[] rooms;
    private long SEED=1223132;
    private Random random;
    private int width=100;
    private int height=50;
    private int xSide=3;
    private int ySide=2;
    public RoomGenerator(){
        random=new Random(SEED);
        rooms=new Path[RandomUtils.uniform(random,20,25)];
    }

    public RoomGenerator(long SEED, TETile[][]world, int x, int y){
        this.SEED=SEED;
        random=new Random(SEED);
        rooms=new Path[RandomUtils.uniform(random,20,25)];
        width=world.length;
        height=world[0].length;
        xSide=x;
        ySide=y;
    }

    private Path createRoom(){
        int xlength=RandomUtils.uniform(random,3,10);
        int ylength=RandomUtils.uniform(random,3,10);
        int xP=RandomUtils.uniform(random,xSide,width-xSide-xlength);
        int yP=RandomUtils.uniform(random,ySide,height-ySide-ylength);
        return createRoomHelper(xP, yP, xlength, ylength);
    }
    private Path createRoomHelper(int xP, int yP, int xLength, int yLength){
        for(int i=0;i<rooms.length;i++){
            if(rooms[i]!=null){
                int xEnd=xP+xLength-1;
                int yEnd=yP+yLength-1;
                boolean xStart=xP>=rooms[i].pointer.x&&xP<=rooms[i].pointer.xEnd;
                boolean xFinish=xEnd>=rooms[i].pointer.x&&xEnd<=rooms[i].pointer.xEnd;
                boolean yStart=yP>=rooms[i].pointer.y&&yP<=rooms[i].pointer.yEnd;
                boolean yFinish=yEnd>=rooms[i].pointer.y&&yEnd<=rooms[i].pointer.yEnd;
                if((xStart&&yStart)||(xStart&&yFinish)||(xFinish&&yStart)||(xFinish&&yFinish)){
                    xP=RandomUtils.uniform(random,xSide,width-xSide-xLength);
                    yP=RandomUtils.uniform(random,ySide,height-ySide-yLength);
                    return createRoomHelper(xP, yP, xLength, yLength);
                }
            }
        }
        return new Path(xP, yP, xLength, yLength);
    }

    public void createRooms(){
        for(int i=0;i<rooms.length;i++){
            rooms[i]=createRoom();
        }
    }

    public Path[] getRooms(){
        return rooms;
    }
}
