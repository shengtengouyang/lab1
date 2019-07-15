package byog.Core;

import java.io.Serializable;
import java.util.Random;

public class HallwayGenerator implements Serializable {
    private static final long serialVersionUID = 4007666385730009166L;
    private Path[] hallways;
    private long SEED=1223132;
    private Random random;
    private static final int WIDTH=100;
    private static final int HEIGHT=50;

    public HallwayGenerator(Path[] rooms) throws NullPointerException{
        random=new Random(SEED);
        hallways=new Path[rooms.length-1];
    }
    public HallwayGenerator(Path[] rooms,long SEED) throws NullPointerException{
        this.SEED=SEED;
        hallways=new Path[rooms.length-1];
        random=new Random(SEED);
    }

    private Path createHallway(Path room1, Path room2){
        Path hallway=randomPoint(room1, room2);
        hallway.setShape(createShape());
        return hallway;
    }

    public void createHallways(Path[]rooms){
        for(int i=0;i<hallways.length;i++){
            hallways[i]=createHallway(rooms[i],rooms[i+1]);
        }
    }

    private Path randomPoint(Path room1, Path room2){
        int randomX=RandomUtils.uniform(random, room1.pointer.x, room1.pointer.xEnd);
        int randomY=RandomUtils.uniform(random, room1.pointer.y, room1.pointer.yEnd);
        int randomX2=RandomUtils.uniform(random, room2.pointer.x, room2.pointer.xEnd);
        int randomY2=RandomUtils.uniform(random, room2.pointer.y, room2.pointer.yEnd);
        int xLength=randomX2-randomX+1;
        int yLength=randomY2-randomY+1;
        return new Path(randomX, randomY, xLength, yLength);
    }
    private Path.Shape createShape(){
        int x=random.nextInt(2);
        if(x==0){
            return Path.Shape.HORIZONTAL;
        }
        else{
            return Path.Shape.VERTICAL;
        }
    }

    public Path[] getHallways(){
        return hallways;
    }
}
