import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;

public class GuitarHeroMine {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    //The “white keys” are on the qwerty and zxcv rows
    //The “black keys” on the 12345 and asdf rows of the keyboard.
    public static void main(String[] args) {
        GuitarString[] strings=new GuitarString[37];
        for(int i=0;i<keyboard.length();i++){
            strings[i]=new GuitarString(440*Math.pow(2,(double)(i-24)/12));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int position=keyboard.indexOf(key);
                if(position>=0){
                    strings[position].pluck();
                }
            }
            double sample=0;
            for(GuitarString move:strings){
                sample+=move.sample();
            }
            StdAudio.play(sample);
            for(GuitarString move:strings){
                move.tic();
            }
        }
    }
}
