package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Map;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private TETile[][] world;
    private MapGenerator map;
    private StringBuilder seedStrings;
    private StringBuilder inputs;
    private boolean win=false;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public Game(){
        seedStrings=new StringBuilder();
        inputs=new StringBuilder();
    }
    public void playWithKeyboard() {
        initial();
        drawMenu();
        operation();
        move(map.getPlayer1());
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
//        // TODO: Fill out this method to run the game using the input passed in,
//        // and return a 2D tile representation of the world that would have been
//        // drawn if the same inputs had been given to playWithKeyboard().
//        initial();
//        char start=Character.toUpperCase(input.charAt(0));
//        switch(start){
//            case 'N':
//                int upper=input.indexOf("S");
//                int lower=input.indexOf("s");
//                int playing;
//                if(upper>0&&lower>0){
//                    playing=Math.min(upper, lower);
//                }
//                else{
//                    playing=Math.max(upper, lower);
//                }
//                if(playing>0){
//                    ter.initialize(WIDTH,HEIGHT);
//                    world = new TETile[WIDTH][HEIGHT];
//                    try{
//                        seedStrings=new StringBuilder(input.substring(0, playing+1));
//                        inputs=new StringBuilder(input.substring(seedStrings.length()));
//                        String seed=seedStrings.substring(1,seedStrings.length()-1);
//                        System.out.println(seed);
//                        drawMap(seed);
//                        map.inputs=inputs;
//                        moveByInputs(map.getPlayer1());
//                        return world;
//                    }
//                    catch(NumberFormatException | StringIndexOutOfBoundsException ex){
//                        drawErrorSeed();
//                        drawSeed(seedStrings.substring(1,seedStrings.length()-1));
//                        return world;
//                    }
//                }
//                break;
//            case 'L':
//                map=loadWorld();
//                if(map==null){
//                    seedStrings=new StringBuilder();
//                    return null;
//                }
//                world=map.world;
//                ter.initialize(WIDTH,HEIGHT);
//                ter.renderFrame(world);
//                return world;
//            case 'Q':
//                System.exit(0);
//        }
//
//        return world;
        return world;
    }


    private void drawMenu() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.CYAN);
        Font font=new Font(Font.SERIF,Font.BOLD,80);
        StdDraw.setFont(font);
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2+12, "Maze Tower");
        font=new Font(Font.SANS_SERIF, Font.ITALIC, 64);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.PINK);
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2, "Press N to start a new game.");
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2-4, "Press L to load a saved game.");
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2-8, "Press Q to quit the game.");
        StdDraw.show();
        StdDraw.pause(1000);
    }

    private void initial(){
        StdDraw.setCanvasSize(WIDTH*16, HEIGHT*16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    private void drawSeed(String seed){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        Font font=new Font(Font.SERIF,Font.ITALIC,64);
        StdDraw.setFont(font);
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2,"Press S to start the game.");
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2-4,"Enter Seed: "+seed);
        StdDraw.show();
        StdDraw.pause(100);
    }

    private void drawErrorSeed(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        Font font=new Font(Font.SERIF,Font.ITALIC,64);
        StdDraw.setFont(font);
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2,"Invalid Seed");
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2-4,"Please Enter valid characters");
        StdDraw.show();
        StdDraw.pause(2000);
        seedStrings.append("N");
        drawSeed("");
    }

    private void operation(){
        while(true){
            if(!StdDraw.hasNextKeyTyped()){
                continue;
            }
            char key=Character.toUpperCase(StdDraw.nextKeyTyped());
            seedStrings.append(key);
            if(seedStrings.toString().contains("Q")){
                System.exit(0);
            }
            if(seedStrings.toString().startsWith("N")){
                switch (key){
                    case 'S':
                        ter.initialize(WIDTH,HEIGHT);
                        world = new TETile[WIDTH][HEIGHT];
                        try{
                            String seed=seedStrings.substring(1,seedStrings.length()-1);
                            drawMap(seed);
                            return;
                        }
                        catch(NumberFormatException | StringIndexOutOfBoundsException ex){
                            drawErrorSeed();
                            continue;
                        }
                    default://include situations of all seeds and 'N' button typed.
                        drawSeed(seedStrings.substring(1));
                }
            }
            else if(seedStrings.toString().startsWith("L")){
                map=loadWorld();
                if(map==null){
                    seedStrings=new StringBuilder();
                    continue;
                }
                world=map.world;
                ter.initialize(WIDTH,HEIGHT);
                ter.renderFrame(world);
                return;
            }
            else{
                seedStrings=new StringBuilder();
            }
        }
    }

//    private void moveByInputs(Player1 player){
//        for(int i=0; i<inputs.length(); i++){
//            char key=inputs.charAt(i);
//            player.move(key);
//            map.inputs.append(key);
//            quitAndSave();
//            map.updateMap(player);
//            ter.renderFrame(map.world);
//        }
//    }

    private void move(Player1 player){
        while(true){
            checkWin();
            if(win){
                drawWin();
                break;
            }
            if(!StdDraw.hasNextKeyTyped()){
                continue;
            }
            char key=Character.toUpperCase(StdDraw.nextKeyTyped());
            player.move(key);
            inputs.append(key);
            map.inputs=inputs;
            quitAndSave();
            map.updateMap(player);
            ter.renderFrame(map.world);
        }
    }

    private void quitAndSave(){
        if(inputs.toString().endsWith(":Q")){
            saveWorld(map);
            System.exit(0);
        }
        else if(inputs.toString().endsWith("Q")){
            System.exit(1);
        }
    }

    private void drawMap(String seed){
        map=new MapGenerator(seed, world);
        map.drawAll();
        ter.renderFrame(map.world);
    }

    private void checkWin(){
        int x=map.getPlayer1().position.x;
        int y=map.getPlayer1().position.y;
        if(world[x][y].equals(Tileset.LOCKED_DOOR)){
            win=true;
        }
    }

    private void drawWin(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.YELLOW);
        Font font=new Font(Font.SERIF,Font.ITALIC,64);
        StdDraw.setFont(font);
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2,"Congratulations!!!");
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2-4,"You Win!!");
        StdDraw.show();
        StdDraw.pause(1000);
        System.exit(2);
    }

    private void saveWorld(MapGenerator m) {
        File f = new File("./world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(m);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private MapGenerator loadWorld() {
        File f = new File("./world.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                MapGenerator loadWorld = (MapGenerator) os.readObject();
                inputs=loadWorld.inputs;
                inputs.delete(inputs.length()-2,inputs.length());
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        else{
            drawErrorLoad();
        }
        return null;
    }

    private void drawErrorLoad(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        Font font=new Font(Font.SERIF,Font.ITALIC,64);
        StdDraw.setFont(font);
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2,"No saved game found");
        StdDraw.text((double)WIDTH/2, (double)HEIGHT/2-4,"Please Start a new Game");
        StdDraw.show();
        StdDraw.pause(2000);
        seedStrings=new StringBuilder();
        drawMenu();
    }
}

