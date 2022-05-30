public class Jetrimonio {
    private int color;
    private int[][][] layouts = {
        {
            {0,0,0,0},
            {1,1,1,1},
            {0,0,0,0},
            {0,0,0,0}
        },
        {
            {0,1,0,0},
            {0,1,0,0},
            {0,1,0,0},
            {0,1,0,0}
        }
    };
    private int rotation = 0;
    private int x = 0;
    private int y = 0;

    public Jetrimonio() {
        // set color
        color = (int)(Math.random() * 5) + 1;
    }

    public void spin() {
        rotation = (rotation + 1) % layouts.length;
    }

    public void moveDown() {
        y += 1;
    }

    public void render() {
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (layouts[rotation][i][j] == 1) {
                    Board.draw_block(j+x, i+y, color);
                }
            }
        }
    }

    public void keyboardInput() {
        // get keyboard input
        if (StdDraw.isKeyPressed(37)) { // left
            x --;
        }
        if (StdDraw.isKeyPressed(39)) { // right
            x ++;
        }
        if (StdDraw.isKeyPressed(38)) { // up
            spin();
            StdDraw.pause(50);
        }
    }
}