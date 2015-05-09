package ca.codemake.CubeBreak;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.helpers.Assets;
import ca.codemake.CubeBreak.state.GameStateManager;
import ca.codemake.CubeBreak.state.PlayState;

public class CubeBreak extends ApplicationAdapter {

    private GameStateManager gsm;
    private SpriteBatch batch;

    private boolean dispose;
    private boolean paused;

	public void create () {
        // Set Libgdx log level to DEBUG
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // Load assets
        Assets.instance.init(new AssetManager());
        // Initialize
        init();
	}

	public void render () {
        // Do not update game when paused
        if (!paused) {
            // Update game world by the time that has passed since last rendered time
            gsm.update(Gdx.graphics.getDeltaTime());
        }
        // Sets the clear screen color to black
        Gdx.gl.glClearColor(1, 1, 1, 1);
        // Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render game to screen
        gsm.render(batch);
	}

    private void init() {
        batch = new SpriteBatch();

        // Game is active on start
        paused = false;

        gsm = new GameStateManager();
        gsm.push(new PlayState(gsm));
    }

    public void resize (int width, int height) {
//        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
//        camera.update();
    }
    public void pause() { paused = true; }

    public void resume() {
        Assets.instance.init(new AssetManager());
        paused = false; }

    public void dispose() {
        Assets.instance.dispose();
    }

}
