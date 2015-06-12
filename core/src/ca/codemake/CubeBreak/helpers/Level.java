package ca.codemake.CubeBreak.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        row = 2;
        col = 2;

//        gridBoard = new GridBoard(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), row, col);

        lives = Constants.LIVES_START;

        score = 0;

        // Objects
//        gridTiles = new GridTile[row][col];

        // load image file that represents the level data
        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));

        // Create Grid Tiles array [row][col]
        gridTiles = new GridTile[pixmap.getHeight()][pixmap.getWidth()];
        System.out.println(pixmap.getHeight() + ", " + pixmap.getWidth());

        row = pixmap.getHeight();
        col = pixmap.getWidth();

        Random rand = new Random();
        int color = 0;

        float size = getSize(row, col);
        System.out.println(size);
//        gridBoard = new GridBoard(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), row, col);
        gridBoard = new GridBoard(0, 0, size * col, size * row, row, col);

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
//                    float heightIncreaseFactor = 0.25f;
                gridTiles[pixelY][pixelX] = (GridTile) obj;
//                System.out.print(color + " ");
            }
//            System.out.println();
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
        mouse.x = mouse.x - gridBoard.xOffset;
        mouse.y = mouse.y - gridBoard.yOffset;

        int c = (int) (mouse.y / gridBoard.SIZE);
        int r = (int) (mouse.x / gridBoard.SIZE);

//        System.out.println(r + ", " + c);

        if (mouse.x > gridBoard.xOffset && mouse.x < (Gdx.graphics.getWidth() - gridBoard.xOffset) && mouse.y > gridBoard.yOffset && mouse.y < (Gdx.graphics.getHeight() - gridBoard.yOffset)) {
            gridBoard.switchTile.add(gridBoard.gridTiles[r][c]);
            System.out.println("Selected Tiles: " + gridBoard.switchTile.size);
            for (GridTile gt : gridBoard.switchTile) {
                System.out.println("Row: " + gt.getRow() + ", Col: " + gt.getCol());
//                gridBoard.gridTiles[r][c].getDetails();
            }
        }
        if (gridBoard.switchTile.size == 2) {
            System.out.println("SWITCH");
            gridBoard.switchTile(gridBoard.switchTile.get(0), gridBoard.switchTile.get(1));
            gridBoard.switchTile.clear();
        }
    }



    public void handleInput(float dt, Vector3 mouse, boolean right) {
//        mouse.x = mouse.x - gridBoard.xOffset;
//        mouse.y = mouse.y - gridBoard.yOffset;

        int r = (int) ((mouse.y - gridBoard.yOffset) / gridBoard.SIZE);
        int c = (int) ((mouse.x - gridBoard.xOffset) / gridBoard.SIZE);

        if(right) {
//            System.out.println("OUT: " + mouse.x + ", " + mouse.y + " | xOffset: " + gridBoard.xOffset + ", yOffset: " + gridBoard.yOffset + " | Row: " + r + ", Col: " + c);
            if (mouse.x > gridBoard.xOffset && mouse.x < (Gdx.graphics.getWidth() - gridBoard.xOffset) && mouse.y > gridBoard.yOffset && mouse.y < (Gdx.graphics.getHeight() - gridBoard.yOffset)) {
//                System.out.println("IN: " + mouse.x + ", " + mouse.y + " | xOffset: " + gridBoard.xOffset + ", yOffset: " + gridBoard.yOffset + " | Row: " + r + ", Col: " + c);
                gridBoard.switchTile.add(gridBoard.gridTiles[r][c]);
                System.out.println("Selected Tiles: " + gridBoard.switchTile.size);
                for (GridTile gt : gridBoard.switchTile) {
                    System.out.println("Row: " + gt.getRow() + ", Col: " + gt.getCol());
//                gridBoard.gridTiles[r][c].getDetails();
                }
            }
            if (gridBoard.switchTile.size == 2) {
                System.out.println("SWITCH");
                gridBoard.switchTile(gridBoard.switchTile.get(0), gridBoard.switchTile.get(1));
                gridBoard.switchTile.clear();
            }
        } else {
            gridBoard.gridTiles[r][c].getTargetDetails();
        }
    }

    public float getSize(int row, int col) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float size = 0;

        if(width > height && row > col) {
            size = height / row;
        } else if(width < height && row < col) {
            size = width / col;
        } else if(width < height && row > col) {
            size = height / row;
        } else if(width > height && row < col) {
            size = width / col;
        } else if(width == height && row > col) {
            size = width / row;
        } else if(width == height && row < col) {
            size = width / col;
        } else if(width > height && row == col) {
            size = height / row;
        } else if(width < height && row == col) {
            size = width / row;
        } else if(width == height && row < col) {
            size = width / col;
        } else if(width == height && row == col) {
            size = width / row;
        }

        return size;
//        System.out.println("Width: " + width + ", Height: " + height + ", Row: " + row + ", Col: " + col + ", Size: " + SIZE);
    }
}
