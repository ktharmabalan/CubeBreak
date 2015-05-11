package ca.codemake.CubeBreak.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.CubeBreak;
import ca.codemake.CubeBreak.ui.GridBoard;

/**
 * Created by Kajan on 4/29/2015.
 */
public class PlayState extends State {

    private GridBoard gridBoard;

    private int row;
    private int col;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    private void init() {
        row = 6;
        col = 10;

        gridBoard = new GridBoard(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), row, col);
    }

    public void update(float dt) {
        handleInput(dt);
        gridBoard.update(dt);
    }

    public void render(SpriteBatch batch) {
        gridBoard.render(batch);
    }

    public void handleInput(float dt) {
//        if(Gdx.app.getType() != Application.ApplicationType.Desktop) return;
        if(Gdx.input.isTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            CubeBreak.camera.unproject(mouse);

            System.out.println(((int)(mouse.x / GridBoard.SIZE)) + ", " + ((int)(mouse.y / GridBoard.SIZE)));
//            System.out.println(gridBoard.gridTiles[(int)(mouse.x / GridBoard.SIZE)][(int)(mouse.y / GridBoard.SIZE)].getColor());
        }

    }
}
