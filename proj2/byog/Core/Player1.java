package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Player1 implements Serializable {
    private static final long serialVersionUID = 181351454198309812L;
    public Pointer position;
    public final int life=5;
    public TETile[][] world;
    public Player1(int x, int y, TETile[][] world){
        position=new Pointer(x,y,x,y);
        this.world=world;
    }

    private boolean update(int x, int y){
        if(x<0||x>world.length-1||y<0||y>world.length-1){
            return false;
        }
        if(world[x][y].equals(Tileset.FLOOR) ){
            world[x][y]=Tileset.PLAYER;
            return true;
        }
        else return world[x][y].equals(Tileset.LOCKED_DOOR);
    }

    public void move(char key){
        int x=position.x;
        int y=position.y;
        boolean update=false;
        switch (key){
            case 'W':
                update=update(x,y+1);
                if(update){
                    position.y=y+1;
                    world[x][y]=Tileset.FLOOR;
                }
                break;
            case 'A':
                update=update(x-1,y);
                if(update){
                    position.x=x-1;
                    world[x][y]=Tileset.FLOOR;
                }
                break;
            case 'S':
                update=update(x,y-1);
                if(update){
                    position.y=y-1;
                    world[x][y]=Tileset.FLOOR;
                }
                break;
            case 'D':
                update=update(x+1,y);
                if(update){
                    position.x=x+1;
                    world[x][y]=Tileset.FLOOR;
                }
        }
    }
}
