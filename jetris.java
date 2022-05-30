public class Jetris {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(22, 0);
        StdDraw.enableDoubleBuffering();

        Board board = new Board();
        Jetrimonio piece = new Jetrimonio();
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