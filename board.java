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
        {106, 76, 147}
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

    public static void draw_block(int x, int y, int c) {
        int[] color = colors[c];
        StdDraw.setPenColor(color[0], color[1], color[2]);
        StdDraw.filledRectangle(x + pad_x + 0.5, y + pad_y + 0.5, 0.5, 0.5);
    }
}