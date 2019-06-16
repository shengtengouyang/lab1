package byog.lab5;
import javafx.geometry.Pos;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final long SEED = 287;
    private static final Random RANDOM = new Random(SEED);
    private static class Position{
        private int x;
        private int y;
        private Position(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    /**add a single hexagon at a specific position */
    private static void addHexagon(TETile[][] world, Position p, int size,TETile graph){
        if (p.x<0||p.x+2*size-2>WIDTH-1||p.y<0||p.y+2*size-1>HEIGHT){
            throw new ArrayIndexOutOfBoundsException("index out of world");
        }
        for(int i=0;i<2*size;i++){
            addRow(world,findCol(i,p.x,size),p.y+i,findWidth(i,size),graph);
        }
    }
    /**add a certain row with row index and starting position of column
     * @param col:the index of starting column number of current row to be added
     * @param row:the actual row index
     * @param width: number of item to be added in current row
     * */
    private static void addRow(TETile[][] world, int col, int row, int width, TETile graph){
        for(int i=col;i<col+width;i++){
            world[i][row]=graph;
        }
    }
    /**calculate the width of a certain row in the hexagon
     * @param row: row index within the hexagon
     * @param size: the size of hexagon
     */
    private static int findWidth(int row, int size){
        if(row<size){
            return size+2*row;
        }
        else{
            int max=size+2*(size-1);
            return max-2*(row-size);
        }
    }
    /**calculate the actual col position for current row of hexagon
     * @param row : row index within hexagon
     * @param start : starting index of column
     * */
    private static int findCol(int row, int start, int size){
        if(row<size){
            return start+(size-row-1);
        }
        else{
            return start+(row-size);
        }
    }
    private static void build(TETile[][] world, Position p, int size){
        for(int i=-2;i<3;i++){
            buildCol(world,findPosition(p,i,size),i,size);
        }
    }

    /**
     * this method help to build a single col of hexagon starting at a specific position
     * @param world the world to build things
     * @param p starting position of current col
     * @param size size of the hexagon
     */
    private static void buildCol(TETile[][] world, Position p, int index, int size){
        int height=findHeight(index);
        for(int i=0;i<height;i++){
            Position newP=new Position(p.x,p.y+i*2*size);
            addHexagon(world,newP,size,randomTile());
        }
    }

    /**
     * find number of hexagons in current col
     * @param index index of current col
     * @return number of hexagons
     */
    private static int findHeight(int index){
        return 5-Math.abs(index);
    }

    /**
     * find starting position of current col
     * @param p starting position of the whole series
     * @param index index of current col
     * @return starting position of current col
     */
    private static Position findPosition(Position p, int index, int size){
        int y=p.y+Math.abs(index)*size;
        int x=p.x+index*(2*size-1);
        return new Position(x,y);
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.SAND;
            default: return Tileset.MOUNTAIN;
        }
    }
    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        build(world,new Position(20,0),3);
        ter.renderFrame(world);
    }
}
