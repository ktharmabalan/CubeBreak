package ca.codemake.CubeBreak.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.helpers.Level;

/**
 * Created by Kajan on 4/29/2015.
 */
public class PlayState extends State {

    public Level level;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    private void init() {
        initLevel();
    }

    private void initLevel() {
        level = new Level(Constants.TWO_X_TWO_TEST);
    }

    public void update(float dt) {
        level.update(dt);
        handleInput(dt);
    }

    public void render(SpriteBatch batch) {
        level.render(batch);
    }

    public void handleInput(float dt) {
//        if(Gdx.app.getType() != Application.ApplicationType.Desktop) return;
        if(Gdx.input.justTouched()) {

            mouse.x = Gdx.input.getX();
            mouse.y = (Gdx.graphics.getHeight() - Gdx.input.getY());

            if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                level.handleInput(dt, mouse, false);
            } else {
//            CubeBreak.camera.unproject(mouse);
                level.handleInput(dt, mouse, true);
//                level.handleInput(dt, mouse);

            }
        }
    }
}
