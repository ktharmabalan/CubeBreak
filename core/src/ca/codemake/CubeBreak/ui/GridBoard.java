package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.helpers.Assets;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridBoard extends AbstractGameObject {

    private TextureRegion darkgrey;
    private TextureRegion lightgrey;
    private ShapeRenderer shapeRenderer;
    private float xOffset;
    private float yOffset;
    public static float SIZE;
    private int row;
    private int col;
    public GridTile[][] gridTiles;
    private Random rand;

    public GridBoard(float x, float y, int row, int col) {
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
        this.position.set(x, y);

        setSize();

        this.dimension.set(col * SIZE, row * SIZE);

        xOffset = (x - this.dimension.x) / 2;
        yOffset = (y - this.dimension.y) / 2;
    }

    private void drawBoard() {
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
//        shapeRenderer.setColor(0, 0, 0, 1);
//        shapeRenderer.rect(0, 0, Constants.WIDTH, Constants.HEIGHT);
//        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(xOffset, yOffset, dimension.x, dimension.y);
        shapeRenderer.end();

//        System.out.println("draw");

    }

    private void setSize() {
        if(Constants.WIDTH > Constants.HEIGHT && row > col) {
            SIZE = Constants.HEIGHT / row;
        } else if(Constants.WIDTH < Constants.HEIGHT && row < col) {
            SIZE = Constants.WIDTH / col;
        } else if(Constants.WIDTH < Constants.HEIGHT && row > col) {
            SIZE = Constants.HEIGHT / row;
        } else if(Constants.WIDTH > Constants.HEIGHT && row < col) {
            SIZE = Constants.WIDTH / col;
        } else if(Constants.WIDTH == Constants.HEIGHT && row > col) {
            SIZE = Constants.WIDTH / row;
        } else if(Constants.WIDTH == Constants.HEIGHT && row < col) {
            SIZE = Constants.WIDTH / col;
        } else if(Constants.WIDTH > Constants.HEIGHT && row == col) {
            SIZE = Constants.HEIGHT / row;
        } else if(Constants.WIDTH == Constants.HEIGHT && row < col) {
            SIZE = Constants.WIDTH / col;
        } else if(Constants.WIDTH == Constants.HEIGHT && row == col) {
            SIZE = Constants.WIDTH / row;
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

                gridTiles[r][c] = new GridTile(color, c * SIZE, r * SIZE, SIZE, SIZE, r, c);
            }
        }
    }

    public void render(SpriteBatch batch) {
        drawBoard();
//        int count = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
//                batch.begin();
//                if (count % 2 == 0)
//                    batch.draw(darkgrey, c * SIZE, r * SIZE, SIZE, SIZE);
//                else
//                    batch.draw(lightgrey, c * SIZE, r * SIZE, SIZE, SIZE);
//                batch.end();

                gridTiles[r][c].render(batch);
//                count++;
            }
//            count++;
        }
    }
}
