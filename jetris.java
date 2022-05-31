import java.io.*;
import javax.sound.sampled.*;
import java.util.*;
import java.awt.Font;

public class Jetris {
    public static void main(String[] args) throws Exception {
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(22, 0);
        StdDraw.enableDoubleBuffering();

        Board board = new Board();
        Jetrimonio piece = getRandomPiece();

        // play music
        File theme_a = new File("tetris.wav");
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(theme_a));
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

        int drop_ctr = 0;
        int drop_cycle_length = 10;

        int score = 0;

        Font font = new Font("Futura", Font.PLAIN, 20);
        StdDraw.setFont(font);

        while (true) {
            StdDraw.clear();
            piece.render();
            piece.keyboardInput();
            board.render();

            if (board.test_collide(piece)) {
                while (board.test_collide(piece)) {
                    piece.moveUp();
                }
                board.freeze_piece(piece);
                piece = getRandomPiece();
                score += 10;
                for (int i=0; i<4; i++) {
                    board.clear_row();
                }
                if (score % 90 == 0) {
                    drop_cycle_length -= 1;
                    if (drop_cycle_length < 2) {
                        drop_cycle_length = 2;
                    }
                }
            }

            drop_ctr ++;
            if (drop_ctr == drop_cycle_length) {
                piece.moveDown();
                drop_ctr = 0;
            }

            StdDraw.text(11, 0.5, "T E T R I S");
            StdDraw.text(3, 11, "SCORE: " + score);
            StdDraw.text(19, 11, "LVL: " + (int)(score / 90));

            StdDraw.show();
            StdDraw.pause(50);
        }
    }

    public static Jetrimonio getRandomPiece() {
        Jetrimonio[] pieces = new Jetrimonio[]{
            new BlockI(),
            new BlockJ(),
            new BlockL(),
            new BlockO(),
            new BlockS(),
            new BlockT(),
            new BlockZ(),
        };
        return pieces[(int)(Math.random() * 7)];
    }
}