package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.Constants;

/**
 * Created by Kajan on 4/29/2015.
 */
public abstract class Entity {

    public float x;
    public float y;
    public float width;
    public float height;

    public OrthographicCamera camera;

    public float speed;

    public Entity() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
    }

//    public abstract void update(float dt);
//    public abstract void render(SpriteBatch batch);

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }


}
