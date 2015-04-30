package ca.codemake.CubeBreak;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.handlers.TextureAtlasLoader;
import ca.codemake.CubeBreak.state.GameStateManager;
import ca.codemake.CubeBreak.state.PlayState;

public class CubeBreak extends ApplicationAdapter {

    private GameStateManager gsm;
    private SpriteBatch batch;

    public static TextureAtlasLoader tal;


	@Override
	public void create () {
        init();
	}

    @Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}

    private void init() {
        tal = new TextureAtlasLoader();
        tal.loadAtlas("pack.pack", "pack");
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        gsm.push(new PlayState(gsm));
    }
}
