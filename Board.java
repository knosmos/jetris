public class Board {
    public static int width = 10;
    public static int height = 20;
    private int[][] state = new int[height][width];

    public static int pad_x = 6;
    public static int pad_y = 1;

    public static int[][] colors = new int[][]{
        {255, 255, 255},
        {255, 89, 94},
        {255, 202, 58},
        {138, 201, 38},
        {25, 130, 196},
        {106, 76, 147},
        {200, 200, 200}
    };

    public Board() {
        initialize();
    }

    // set all values in state = 0
    public void initialize() {
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                state[i][j] = 0;
            }
        }
    }

    public void render() {
        // draw blocks
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (state[i][j] != 0) {
                    draw_block(j, i, state[i][j]);
                }
            }
        }

        // draw grid
        StdDraw.setPenColor(0, 0, 0);
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                StdDraw.rectangle(j + pad_x + 0.5, i + pad_y + 0.5, 0.5, 0.5);   
            }
        }
    }

    public void freeze_piece(Jetrimonio piece) {
        // integrates piece into state
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (piece.getLayout()[i][j] == 1) {
                    state[i + piece.getY()][j + piece.getX()] = piece.getColor();
                }
            }
        }
    }

    public boolean test_collide(Jetrimonio piece) {
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (piece.getLayout()[i][j] == 1) {
                    if (piece.getY() + i >= height) {
                        return true;
                    }
                    if (state[i + piece.getY()][j + piece.getX()] != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean clear_row() {
        for (int y=0; y<height; y++) {
            boolean clear = true;
            for (int x=0; x<width; x++) {
                if (state[y][x] == 0) {
                    clear = false;
                    break;
                }
            }
            if (clear) {
                StdOut.println("clearing row");
                // clear row
                for (int x=0; x<width; x++) {
                    state[y][x] = 0;
                }
                // move rows down
                for (int i=y-1; i>=0; i--) {
                    for (int x=0; x<width; x++) {
                        state[i+1][x] = state[i][x];
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void draw_block(int x, int y, int c) {
        int[] color = colors[c];
        StdDraw.setPenColor(color[0], color[1], color[2]);
        StdDraw.filledRectangle(x + pad_x + 0.5, y + pad_y + 0.5, 0.5, 0.5);
        StdDraw.setPenColor(Math.min(255, color[0]+40), Math.min(255, color[1]+40), Math.min(255, color[2]+40));
        StdDraw.filledRectangle(x + pad_x + 0.3, y + pad_y + 0.3, 0.2, 0.2);
    }
}
