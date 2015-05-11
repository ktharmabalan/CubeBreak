package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kajan on 5/10/2015.
 */
public abstract class AbstractGameObject {
    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    public AbstractGameObject() {
        position = new Vector2();
        dimension = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;
    }

    public void update(float dt) {}

    public abstract void render (SpriteBatch batch);

    public float getX() { return position.x; }
    public float getY() { return position.y; }
    public float getWidth() { return dimension.x; }
    public float getHeight() { return dimension.y; }
}
