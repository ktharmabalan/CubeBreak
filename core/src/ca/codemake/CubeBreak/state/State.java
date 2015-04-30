package ca.codemake.CubeBreak.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import ca.codemake.CubeBreak.Constants;

/**
 * Created by Kajan on 4/29/2015.
 */
public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera camera;
    protected Vector3 mouse;

    protected State(GameStateManager gsm) {
        init(gsm);
    }

    private void init(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        mouse = new Vector3();
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void handleInput(float dt);
}
