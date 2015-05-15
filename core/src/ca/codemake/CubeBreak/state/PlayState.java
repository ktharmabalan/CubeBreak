package ca.codemake.CubeBreak.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.CubeBreak;
import ca.codemake.CubeBreak.helpers.Level;
import ca.codemake.CubeBreak.ui.GridBoard;

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
        level = new Level(Constants.LEVEL_TEST);
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
        if(Gdx.input.isTouched()) {

//            mouse.x = (Gdx.graphics.getWidth() - Gdx.input.getX());
            mouse.x = Gdx.input.getX();
//            mouse.y = Gdx.input.getY();
            mouse.y = (Gdx.graphics.getHeight() - Gdx.input.getY());
//            CubeBreak.camera.unproject(mouse);

//            System.out.println(Gdx.input.getX() + "|" + Gdx.input.getY());
//            System.out.println(mouse.x + "|" + mouse.y);
            level.handleInput(dt, mouse);
//            System.out.println(GridBoard.SIZE);
//            System.out.println(mouse);
//            System.out.println(((int)(mouse.x / GridBoard.SIZE)) + ", " + ((int)(mouse.y / GridBoard.SIZE)));
//            System.out.println(gridBoard.gridTiles[(int)(mouse.x / GridBoard.SIZE)][(int)(mouse.y / GridBoard.SIZE)].getColor());
        }

    }
}
