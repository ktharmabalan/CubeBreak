package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ca.codemake.CubeBreak.Constants;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridBoard extends Entity {

    private ShapeRenderer shapeRenderer;
    private float xOffset;
    private float yOffset;
    private float size;
    private int row;
    private int col;
    private GridTile[][] gridTiles;
    private Random rand;

    public GridBoard(float x, float y, int row, int col) {
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        init(x, y, row, col);

        gridTiles = new GridTile[row][col];
        System.out.println(gridTiles.length + ", " + gridTiles[0].length);

        drawBoard();

    }

    private void init(float x, float y, int row, int col) {
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;

        setSize();

        width = col * size;
        height = row * size;

        xOffset = (x - width) / 2;
        yOffset = (y - height) / 2;
    }

    private void drawBoard() {
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        shapeRenderer.setColor(0, 0, 0, 0);
        shapeRenderer.rect(xOffset, yOffset, width, height);
        shapeRenderer.end();

        System.out.println("draw");

        createGrid();
    }

    private void setSize() {
        if(Constants.WIDTH > Constants.HEIGHT && row > col) {
            size = Constants.HEIGHT / row;
        } else if(Constants.WIDTH < Constants.HEIGHT && row < col) {
            size = Constants.WIDTH / col;
        } else if(Constants.WIDTH < Constants.HEIGHT && row > col) {
            size = Constants.HEIGHT / row;
        } else if(Constants.WIDTH > Constants.HEIGHT && row < col) {
            size = Constants.WIDTH / col;
        } else if(Constants.WIDTH == Constants.HEIGHT && row > col) {
            size = Constants.WIDTH / row;
        } else if(Constants.WIDTH == Constants.HEIGHT && row < col) {
            size = Constants.WIDTH / col;
        } else if(Constants.WIDTH > Constants.HEIGHT && row == col) {
            size = Constants.HEIGHT / row;
        } else if(Constants.WIDTH == Constants.HEIGHT && row < col) {
            size = Constants.WIDTH / col;
        } else if(Constants.WIDTH == Constants.HEIGHT && row == col) {
            size = Constants.WIDTH / row;
        }
    }

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
                color = rand.nextInt(3 - 1 + 1) + 1;
                System.out.println("Creating " + r + ", " + c);

                gridTiles[r][c] = new GridTile(color, c * size, r * size, size, size, r, c);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                gridTiles[r][c].render(batch);
            }
        }
    }
}
