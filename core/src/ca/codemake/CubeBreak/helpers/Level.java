package ca.codemake.CubeBreak.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import ca.codemake.CubeBreak.ui.GridTile;

/**
 * Created by Kajan on 5/10/2015.
 */
public class Level {
    public static final String TAG = Level.class.getName();

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
    public Array<GridTile> gridTiles;

    public Level (String filename) {
        init(filename);
    }

    private void init (String filename) {
        // Objects
        gridTiles = new Array<GridTile>();

        // load image file that represents the level data
        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
        // scan pixels from top-left to bottom-right
        for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
            for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {

            }
        }

    }

    public void render (SpriteBatch batch) {}

}
