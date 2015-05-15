package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

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
    private float width;
    private float height;
    private int row;
    private int col;
    public GridTile[][] gridTiles;
    private Random rand;

    public GridBoard(float x, float y, float width, float height, int row, int col) {
//        Array<TextureRegion> regions = new Array<TextureRegion>();
//        regions.add(Assets.instance.tiles.darkgrey);
//        regions.add(Assets.instance.tiles.lightgrey);

        darkgrey = Assets.instance.tiles.darkgrey;
        lightgrey = Assets.instance.tiles.lightgrey;

        init(x, y, width, height, row, col);

        this.gridTiles = new GridTile[row][col];
        this.gridTiles = gridTiles;
//        System.out.println(gridTiles.length + ", " + gridTiles[0].length);

        drawBoard();
//        createGrid();

//        setGrid(gridTiles);
    }

    public void setGrid(GridTile[][] gridTiles) {
        this.gridTiles = gridTiles;
        System.out.println("SET");
    }

    private void init(float x, float y, float width, float height, int row, int col) {
        this.width = width;
        this.height = height;
        this.row = row;
        this.col = col;
        position.set(x, y);

        setSize();

        dimension.set(width, height);

        this.width = col * SIZE;
        this.height = row * SIZE;

        dimension.set(this.width, this.height);

        xOffset = (Gdx.graphics.getWidth() - this.dimension.x) / 2;
        yOffset = (Gdx.graphics.getHeight() - this.dimension.y) / 2;
        position.set(xOffset, yOffset);
        System.out.println("xOffset: " + xOffset + ", yOffset: " + yOffset);
    }

    private void drawBoard() {
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.setColor(1, 1, 0, 1);
//        shapeRenderer.rect(0, 0, width, height);
//        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(xOffset, yOffset, dimension.x, dimension.y);
        shapeRenderer.end();

//        System.out.println("draw");

    }

    private void setSize() {
        if(width > height && row > col) {
            SIZE = height / row;
        } else if(width < height && row < col) {
            SIZE = width / col;
        } else if(width < height && row > col) {
            SIZE = height / row;
        } else if(width > height && row < col) {
            SIZE = width / col;
        } else if(width == height && row > col) {
            SIZE = width / row;
        } else if(width == height && row < col) {
            SIZE = width / col;
        } else if(width > height && row == col) {
            SIZE = height / row;
        } else if(width < height && row == col) {
            SIZE = width / row;
        } else if(width == height && row < col) {
            SIZE = width / col;
        } else if(width == height && row == col) {
            SIZE = width / row;
        }
        System.out.println("Width: " + width + ", Height: " + height + ", Row: " + row + ", Col: " + col + ", Size: " + SIZE);
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
