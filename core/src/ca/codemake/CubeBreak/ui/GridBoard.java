package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ca.codemake.CubeBreak.helpers.Assets;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridBoard extends AbstractGameObject {

    private ShapeRenderer shapeRenderer;
    public float xOffset;
    public float yOffset;
    public static float SIZE;
    private float width;
    private float height;
    private int row;
    private int col;
    public static int maxRow;
    public static int maxCol;
    public GridTile[][] gridTiles;
    private GridTile tempTile;
    private Random rand;

    public Array<GridTile> switchTile;

    public GridBoard(float x, float y, float width, float height, int row, int col) {
        init(x, y, width, height, row, col);

        this.gridTiles = new GridTile[row][col];

        drawBoard();
//        createGrid();

//        setGrid(gridTiles);
        switchTile = new Array<GridTile>();
    }

    public void setGrid(GridTile[][] gridTiles) {
        this.gridTiles = gridTiles;
//        System.out.println("SET");
    }

    private void init(float x, float y, float width, float height, int row, int col) {
        this.width = width;
        this.height = height;
        this.row = row;
        this.col = col;
        position.set(x, y);

//        setSize();

        dimension.set(width, height);

        SIZE = width / col;
        this.width = col * SIZE;
        this.height = row * SIZE;

        dimension.set(this.width, this.height);

        xOffset = (Gdx.graphics.getWidth() - this.dimension.x) / 2;
        yOffset = (Gdx.graphics.getHeight() - this.dimension.y) / 2;
//        xOffset = 0;
//        yOffset =0;
        position.set(xOffset, yOffset);
//        System.out.println("xOffset: " + xOffset + ", yOffset: " + yOffset);
//        System.out.println("window height: " + Gdx.graphics.getHeight());
//        System.out.println("width: " + width);
//        System.out.println("height: " + height);
    }

    private void drawBoard() {
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 1, 1);
//        shapeRenderer.setColor(0, 0, 0, 1);
//        shapeRenderer.setColor(1, 1, 0, 1);
//        shapeRenderer.rect(0, 0, width, height);
//        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(xOffset, yOffset, dimension.x, dimension.y);
//        shapeRenderer.rect(0, 0, 100, 100);
        shapeRenderer.end();

//        System.out.println("draw");

    }

//    private void setSize() {
//        if(width > height && row > col) {
//            SIZE = height / row;
//        } else if(width < height && row < col) {
//            SIZE = width / col;
//        } else if(width < height && row > col) {
//            SIZE = height / row;
//        } else if(width > height && row < col) {
//            SIZE = width / col;
//        } else if(width == height && row > col) {
//            SIZE = width / row;
//        } else if(width == height && row < col) {
//            SIZE = width / col;
//        } else if(width > height && row == col) {
//            SIZE = height / row;
//        } else if(width < height && row == col) {
//            SIZE = width / row;
//        } else if(width == height && row < col) {
//            SIZE = width / col;
//        } else if(width == height && row == col) {
//            SIZE = width / row;
//        }
////        System.out.println("Width: " + width + ", Height: " + height + ", Row: " + row + ", Col: " + col + ", Size: " + SIZE);
//    }

    public void update(float dt) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                gridTiles[r][c].update(dt);
            }
        }
    }

    private void createGrid() {
        System.out.println("create");

        int color;
        rand = new Random();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                color = rand.nextInt(4 - 1 + 1) + 1;
//                System.out.println("Creating " + r + ", " + c);

                gridTiles[r][c] = new GridTile(color, c * SIZE, r * SIZE, SIZE, SIZE, r, c);
            }
        }
    }

    public void render(SpriteBatch batch) {
        drawBoard();
        int count = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                batch.begin();
                if (count % 2 == 0)
                    batch.draw(Assets.instance.tiles.darkgrey, gridTiles[r][c].getX(), gridTiles[r][c].getY(), gridTiles[r][c].getWidth(), gridTiles[r][c].getHeight());
                else
                    batch.draw(Assets.instance.tiles.lightgrey, gridTiles[r][c].getX(), gridTiles[r][c].getY(), gridTiles[r][c].getWidth(), gridTiles[r][c].getHeight());
                batch.end();
                gridTiles[r][c].render(batch);
                count++;
            }
            count++;
        }
    }

    public void switchTile(GridTile tile1, GridTile tile2) {
        int moves = 0;
        boolean left, right, up, down;
        left = right = up = down = false;

        if(tile1.getRow() - 1 == tile2.getRow()) {
            moves++;
            down = true;
        } else if(tile1.getRow() + 1 == tile2.getRow()) {
            moves++;
            up = true;
        }
        if(tile1.getCol() - 1 == tile2.getCol()) {
            moves++;
            left = true;
        } else if(tile1.getCol() + 1 == tile2.getCol()) {
            moves++;
            right = true;
        }

        if(moves == 1) {
            tile1.target.color = tile2.original.color;
            tile2.target.color = tile1.original.color;

            if(up) {
//                System.out.println("UP");
                tile1.setTargetY(tile2.getY());
                tile2.setTargetY(tile1.getY());

//                tile1.setTargetRow(tile2.getRow());
//                tile2.setTargetRow(tile1.getRow());
            } else if(down) {
//                System.out.println("DOWN");
                tile1.setTargetY(tile2.getY());
                tile2.setTargetY(tile1.getY());

//                tile1.setTargetRow(tile2.getRow());
//                tile2.setTargetRow(tile1.getRow());
            } else if(left) {
//                System.out.println("LEFT");
                tile1.setTargetX(tile2.getX());
                tile2.setTargetX(tile1.getX());

//                tile1.setTargetCol(tile2.getCol());
//                tile2.setTargetCol(tile1.getCol());
            } else if(right) {
//                System.out.println("RIGHT");
                tile1.setTargetX(tile2.getX());
                tile2.setTargetX(tile1.getX());

//                tile1.setTargetCol(tile2.getCol());
//                tile2.setTargetCol(tile1.getCol());
            }
        }
    }
}
