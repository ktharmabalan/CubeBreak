package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ca.codemake.CubeBreak.helpers.Assets;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridTile extends AbstractGameObject {

    private int row;
    private int col;

    private int color;

    public GridTile(int color, float x, float y, float width, float height, int row, int col) {
        init(color, x, y, width, height, row, col);
    }

    private void init(int color, float x, float y, float width, float height, int row, int col) {
        this.color = color;
        dimension.set(width, height);
        position.set(x, y);
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
        this.row = row;
        this.col = col;
    }

    public void update(float dt) {
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        drawTile(batch);
//        if (row != 0)
        if(getColor() != 0)
            batch.draw(Assets.instance.dividers.darkbar16, getX() + 1, getY(), getWidth() - 2, 1);
//        if (col != 0)
        if(getColor() != 0)
            batch.draw(Assets.instance.dividers.lightbar16, getX(), getY() + 1, 1, getHeight() - 2);
        batch.end();
    }

    private void drawTile(SpriteBatch batch) {
        if (getDrawColor() == 1) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());

            batch.draw(Assets.instance.tiles.red, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 2) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.green, getX(), getY(), getWidth(), getHeight());
//            batch.setColor(0, 0, 0, 0.25f);
//            batch.draw(alpha, getX(), getY(), getWidth(), getHeight());
//            batch.setColor(1, 1, 1, 1);
        } else if (getDrawColor() == 3) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.blue, getX(), getY(), getWidth(), getHeight());
//            batch.draw(largeglow, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 4) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.black, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 0) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.nothing, getX(), getY(), getWidth(), getHeight());
        }
    }

    private int getDrawColor() {
        return color;
    }

    public int getColor() { return getDrawColor(); }
}
