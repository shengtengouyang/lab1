package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;

public class MapGenerator implements Serializable {
    private static final long serialVersionUID = -2527080879197414928L;
    private RoomGenerator room;
    private HallwayGenerator hallway;
    private final int X_SIDE=3;
    private final int Y_SIDE=2;
    private Random random;
    private Player1 player1;
    public StringBuilder inputs;
    public TETile[][] world;
    public MapGenerator(String seed, TETile[][] world){
        long SEED=parseToLong(seed);
        room=new RoomGenerator(SEED, world, X_SIDE, Y_SIDE);
        random=new Random(SEED);
        this.world=world;
        room.createRooms();
        hallway=new HallwayGenerator(room.getRooms(),SEED);
        hallway.createHallways(room.getRooms());
    }

    private long parseToLong(String SEED){
        String numbers="";
        for(int i=0; i<SEED.length(); i++){
            char x=SEED.charAt(i);
            long number=Character.getNumericValue(x);
            numbers+=Long.toString(number);
        }
        return Long.parseLong(numbers);
    }

    private void fillAll(){
        for(int i=0; i<world.length; i++){
            for (int j=0; j<world[0].length; j++){
                world[i][j]=Tileset.NOTHING;
            }
        }
    }
    private void drawRooms(){
        Path[] rooms=room.getRooms();
        for(Path room:rooms){
            for(int x = room.pointer.x; x<=room.pointer.xEnd; x++){
                for (int y = room.pointer.y; y<=room.pointer.yEnd; y++){
                    world[x][y]= Tileset.FLOOR;
                }
            }
        }
    }

    private void drawHallways(){
        Path[] hallways=hallway.getHallways();
        for(Path hallway:hallways){
            int x=hallway.pointer.x;
            int xEnd=hallway.pointer.xEnd;
            int y=hallway.pointer.y;
            int yEnd=hallway.pointer.yEnd;
            if(hallway.getShape()== Path.Shape.VERTICAL){
                while(x!=xEnd){
                    world[x][y]=Tileset.FLOOR;
                    if(x<xEnd){
                        x++;
                    }
                    else{
                        x--;
                    }
                }
                while(y!=yEnd){
                    world[x][y]=Tileset.FLOOR;
                    if(y<yEnd){
                        y++;
                    }
                    else{
                        y--;
                    }
                }
                world[xEnd][yEnd]=Tileset.FLOOR;
            }
            else{
                while(y!=yEnd){
                    world[x][y]=Tileset.FLOOR;
                    if(y<yEnd){
                        y++;
                    }
                    else{
                        y--;
                    }
                }
                while(x!=xEnd){
                    world[x][y]=Tileset.FLOOR;
                    if(x<xEnd){
                        x++;
                    }
                    else{
                        x--;
                    }
                }
                world[xEnd][yEnd]=Tileset.FLOOR;
            }
        }
    }

    private void drawWalls(){
        for(int i=X_SIDE;i<world.length-X_SIDE; i++) {
            for (int j = Y_SIDE; j < world[0].length - Y_SIDE; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (world[x][y] == Tileset.NOTHING) {
                                world[x][y] = Tileset.WALL;
                            }
                        }
                    }
                }
            }
        }
    }

    private void drawLockedDoor(){
        int x=random.nextInt(world.length);
        int y=random.nextInt(world[0].length);
        if(world[x][y]!=Tileset.FLOOR){
            drawLockedDoor();
        }
        else{
            world[x][y]=Tileset.LOCKED_DOOR;
        }
    }

    private void drawPlayer(){
        int x=random.nextInt(world.length);
        int y=random.nextInt(world[0].length);
        if(world[x][y]!=Tileset.FLOOR){
            drawPlayer();
        }
        else{
            world[x][y]=Tileset.PLAYER;
            player1=new Player1(x,y, world);
        }
    }

    public void drawAll(){
        fillAll();
        drawRooms();
        drawHallways();
        drawWalls();
        drawLockedDoor();
        drawPlayer();
    }

    public void updateMap(Player1 player){
        this.world=player.world;
    }

    public void setPlayer1(Player1 player1){
        this.player1=player1;
    }

    public Player1 getPlayer1(){
        return player1;
    }
}
