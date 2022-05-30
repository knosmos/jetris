import java.io.*;
import javax.sound.sampled.*;

public class Jetris {
    public static void main(String[] args) throws Exception {
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(22, 0);
        StdDraw.enableDoubleBuffering();

        Board board = new Board();
        Jetrimonio piece = new Jetrimonio();

        File theme_a = new File("tetris.wav");
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(theme_a));
        clip.start();

        int drop_ctr = 0;
        int drop_cycle_length = 10;

        while (true) {
            StdDraw.clear();
            piece.render();
            piece.keyboardInput();
            board.render();

            StdDraw.show();
            StdDraw.pause(50);
        }
    }
}