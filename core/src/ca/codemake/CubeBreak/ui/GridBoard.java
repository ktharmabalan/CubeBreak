package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ca.codemake.CubeBreak.Constants;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridBoard extends Entity {

    private ShapeRenderer shapeRenderer;
    private float xOffset;
    private float yOffset;
    private float size;
    private float row;
    private float col;

    public GridBoard(float x, float y, float width, float height, float row, float col, float size) {
        init(x, y, width, height, row, col, size);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
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

        System.out.println(size);
    }

    private void init(float x, float y, float width, float height, float row, float col, float size) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.row = row;
        this.col = col;
        this.size = size;

        setSize();


        this.width = col * this.size;
        this.height = row * this.size;

        xOffset = (Constants.WIDTH - this.width) / 2;
        yOffset = (Constants.HEIGHT - this.height) / 2;
    }

    private void drawBoard() {
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(xOffset, yOffset, width, height);
        shapeRenderer.end();

    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {
        drawBoard();
    }
}
