package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.CubeBreak;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridTile extends Entity {

    private int row;
    private int col;

    private int color;

    private TextureRegion red;
    private TextureRegion green;
    private TextureRegion blue;
    private TextureRegion background;
    private TextureRegion alpha;
    private TextureRegion largeglow;
    private TextureRegion smallglow;
    private TextureRegion nothing;

    public GridTile(int color, float x, float y, float width, float height, int row, int col) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        red = CubeBreak.tal.getAtlas("pack").findRegion("red");
        green = CubeBreak.tal.getAtlas("pack").findRegion("green");
        blue = CubeBreak.tal.getAtlas("pack").findRegion("blue");
        background = CubeBreak.tal.getAtlas("pack").findRegion("background");
        alpha = CubeBreak.tal.getAtlas("pack").findRegion("alpha");
        largeglow = CubeBreak.tal.getAtlas("pack").findRegion("largeglow");
        smallglow = CubeBreak.tal.getAtlas("pack").findRegion("smallglow");
        nothing = CubeBreak.tal.getAtlas("pack").findRegion("nothing");

        init(color, x, y, width, height, row, col);
    }

    private void init(int color, float x, float y, float width, float height, int row, int col) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.row = row;
        this.col = col;
    }

    public void update(float dt) {
    }

    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawTile(batch);
        batch.end();
    }

    private void drawTile(SpriteBatch batch) {
        if (getDrawColor() == 1) {
            batch.draw(red, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 2) {
            batch.draw(green, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 3) {
            batch.draw(blue, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 4) {
            batch.draw(alpha, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 5) {
            batch.draw(largeglow, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 6) {
            batch.draw(smallglow, getX(), getY(), getWidth(), getHeight());
        }
    }

    private int getDrawColor() {
        return color;
    }
}
