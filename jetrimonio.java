public class Jetrimonio {
    private int color;
    public int[][][] layouts = {
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
        while (testLeftWall()) {
            x ++;
        }
        while (testRightWall()) {
            x --;
        }
    }

    public void moveDown() {
        y += 1;
    }

    public void moveUp() {
        y -= 1;
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

    public void render_grey() {
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (layouts[rotation][i][j] == 1) {
                    Board.draw_block(j+x, i+y, 6);
                }
            }
        }
    }

    public void render_shadow(Board board) {
        int original_y = y;
        while (!board.test_collide(this)) {
            moveDown();
        }
        moveUp();
        render_grey();
        y = original_y;
    }

    private boolean testLeftWall() {
        // returns true if any part of the piece is touching the left wall
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (layouts[rotation][i][j] == 1) {
                    if (j+x < 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean testRightWall() {
        // returns true if any part of the piece is touching the right wall
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (layouts[rotation][i][j] == 1) {
                    if (j+x >= Board.width) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void keyboardInput() {
        if (StdDraw.isKeyPressed(65)) { // left
            x --;
            if (testLeftWall()) {
                x ++;
            }
        }
        if (StdDraw.isKeyPressed(68)) { // right
            x ++;
            if (testRightWall()) {
                x --;
            }
        }
        if (StdDraw.isKeyPressed(83)) {
            moveDown();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public int[][] getLayout() {
        return layouts[rotation];
    }
}