package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.CubeBreak;
import ca.codemake.CubeBreak.helpers.Assets;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridBoard extends Entity {

    private TextureRegion darkgrey;
    private TextureRegion lightgrey;
    private ShapeRenderer shapeRenderer;
    private float xOffset;
    private float yOffset;
    private float size;
    private int row;
    private int col;
    private GridTile[][] gridTiles;
    private Random rand;

    public GridBoard(float x, float y, int row, int col) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

//        Array<TextureRegion> regions = new Array<TextureRegion>();
//        regions.add(Assets.instance.tiles.darkgrey);
//        regions.add(Assets.instance.tiles.lightgrey);

        darkgrey = Assets.instance.tiles.darkgrey;
        lightgrey = Assets.instance.tiles.lightgrey;

        init(x, y, row, col);

        gridTiles = new GridTile[row][col];
        System.out.println(gridTiles.length + ", " + gridTiles[0].length);

        drawBoard();
        createGrid();

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
//        shapeRenderer.setColor(0, 0, 0, 1);
//        shapeRenderer.rect(0, 0, Constants.WIDTH, Constants.HEIGHT);
//        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(xOffset, yOffset, width, height);
        shapeRenderer.end();

//        System.out.println("draw");

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
                color = rand.nextInt(4 - 1 + 1) + 1;
//                System.out.println("Creating " + r + ", " + c);

                gridTiles[r][c] = new GridTile(color, c * size, r * size, size, size, r, c);
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        drawBoard();
//        int count = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
//                batch.begin();
//                if (count % 2 == 0)
//                    batch.draw(darkgrey, c * size, r * size, size, size);
//                else
//                    batch.draw(lightgrey, c * size, r * size, size, size);
//                batch.end();

                gridTiles[r][c].render(batch);
//                count++;
            }
//            count++;
        }
    }
}
