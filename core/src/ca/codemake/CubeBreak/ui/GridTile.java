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
    private TextureRegion redhead;
    private TextureRegion greenhead;
    private TextureRegion bluehead;

    public GridTile(int color, float x, float y, float width, float height, int row, int col) {
        red = CubeBreak.tal.getAtlas("pack").findRegion("red");
        green = CubeBreak.tal.getAtlas("pack").findRegion("green");
        blue = CubeBreak.tal.getAtlas("pack").findRegion("blue");
        redhead = CubeBreak.tal.getAtlas("pack").findRegion("redhead");
        greenhead = CubeBreak.tal.getAtlas("pack").findRegion("greenhead");
        bluehead = CubeBreak.tal.getAtlas("pack").findRegion("bluehead");

        init(color, x, y, width, height, row, col);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
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

    }
}
