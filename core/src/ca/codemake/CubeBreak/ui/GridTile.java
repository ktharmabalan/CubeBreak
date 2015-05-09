package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.CubeBreak;
import ca.codemake.CubeBreak.helpers.Assets;

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
    private TextureRegion black;
    private TextureRegion darkbar16;
    private TextureRegion lightbar16;
    private TextureRegion select;

    public GridTile(int color, float x, float y, float width, float height, int row, int col) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        red = Assets.instance.tiles.red;
        green = Assets.instance.tiles.green;
        blue = Assets.instance.tiles.blue;
        black = Assets.instance.tiles.black;
        select = Assets.instance.tiles.select;
        darkbar16 = Assets.instance.dividers.darkbar16;
        lightbar16 = Assets.instance.dividers.lightbar16;

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
        if (row != 0)
        batch.draw(darkbar16, getX() + 1, getY(), getWidth() - 2, 1);
        if (col != 0)
        batch.draw(lightbar16, getX(), getY() + 1, 1, getHeight() - 2);
        batch.end();
    }

    private void drawTile(SpriteBatch batch) {
        if (getDrawColor() == 1) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());

            batch.draw(red, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 2) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(green, getX(), getY(), getWidth(), getHeight());
//            batch.setColor(0, 0, 0, 0.25f);
//            batch.draw(alpha, getX(), getY(), getWidth(), getHeight());
//            batch.setColor(1, 1, 1, 1);
        } else if (getDrawColor() == 3) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(blue, getX(), getY(), getWidth(), getHeight());
//            batch.draw(largeglow, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 4) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(black, getX(), getY(), getWidth(), getHeight());
        }
    }

    private int getDrawColor() {
        return color;
    }
}
