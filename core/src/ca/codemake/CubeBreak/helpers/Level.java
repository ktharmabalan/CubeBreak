package ca.codemake.CubeBreak.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.ui.AbstractGameObject;
import ca.codemake.CubeBreak.ui.GridBoard;
import ca.codemake.CubeBreak.ui.GridTile;

/**
 * Created by Kajan on 5/10/2015.
 */
public class Level {
    public static final String TAG = Level.class.getName();

    public int lives;
    public int score;

    private GridBoard gridBoard;

    private int row;
    private int col;

    public enum BLOCK_TYPE {
        EMPTY(0, 0, 0), // black
        GRID_TILE(0, 38, 255); // green

        private int color;

        private BLOCK_TYPE(int r, int g, int b) {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }

        public boolean sameColor (int color) {
            return this.color == color;
        }

        public int getColor() {
            return color;
        }
    }

    // Objects
    public GridTile[][] gridTiles;

    public Level (String filename) {
        init(filename);
    }

    private void init (String filename) {
        row = 6;
        col = 10;

//        gridBoard = new GridBoard(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), row, col);

        lives = Constants.LIVES_START;

        score = 0;

        // Objects
//        gridTiles = new GridTile[row][col];

        // load image file that represents the level data
        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));

        gridTiles = new GridTile[pixmap.getWidth()][pixmap.getHeight()];
        System.out.println(pixmap.getWidth() + ", " + pixmap.getHeight());

        row = pixmap.getWidth();
        col = pixmap.getHeight();

        Random rand = new Random();
        int color = 0;

        gridBoard = new GridBoard(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), row, col);

        // scan pixels from top-left to bottom-right
        for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
            for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
                AbstractGameObject obj = null;
                // height grows from bottom to top
                float baseHeight = pixmap.getHeight() - pixelY;
                // get color of current pixel as 32-bit RGBA value
                int currentPixel = pixmap.getPixel(pixelX, pixelY);
                // find matching color value to identify block type at (x,y)
                // point and create the corresponding game object if there is a match

                // empty space
                if(BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
                    // do nothing
                    color = 0;
                }
                // Grid Tile
                else if(BLOCK_TYPE.GRID_TILE.sameColor(currentPixel)) {
                    color = rand.nextInt(4 - 1 + 1) + 1;
                }

                obj = new GridTile(color, pixelX * GridBoard.SIZE + gridBoard.getX(), pixelY * GridBoard.SIZE + gridBoard.getY(), GridBoard.SIZE, GridBoard.SIZE, pixelX, pixelY);
//                    float heighIncreaseFactor = 0.25f;
                gridTiles[pixelX][pixelY] = (GridTile)obj;
                System.out.print(color + " ");
            }
            System.out.println();
        }
        gridBoard.setGrid(gridTiles);
    }

    public void render (SpriteBatch batch) {
        gridBoard.render(batch);
    }

    public void update(float dt) {
        gridBoard.update(dt);
    }

    public void handleInput(float dt, Vector3 mouse) {
//        if(Gdx.app.getType() != Application.ApplicationType.Desktop) return;
//        if(Gdx.input.isTouched()) {
//            mouse.x = Gdx.input.getX();
//            mouse.y = Gdx.input.getY();
//            CubeBreak.camera.unproject(mouse);

            System.out.println(((int)(mouse.x / GridBoard.SIZE)) + ", " + ((int)(mouse.y / GridBoard.SIZE)));
//            System.out.println(gridBoard.gridTiles[(int)(mouse.x / GridBoard.SIZE)][(int)(mouse.y / GridBoard.SIZE)].getColor());
//        }

    }
}
