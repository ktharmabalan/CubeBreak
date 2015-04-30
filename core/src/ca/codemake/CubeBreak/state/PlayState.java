package ca.codemake.CubeBreak.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.ui.GridBoard;

/**
 * Created by Kajan on 4/29/2015.
 */
public class PlayState extends State {

    private GridBoard gridBoard;

    private float size;
    private float row;
    private float col;
    private float width;
    private float height;


    public PlayState(GameStateManager gsm) {
        super(gsm);

        init();
    }

    private void init() {
        size = 10;
        row = 5;
        col = 1;
        width = size * col;
        height = size * row;

        float x = (Constants.WIDTH / 2) - width;
        float y = (Constants.HEIGHT / 2) - height;

        gridBoard = new GridBoard(x, y, width, height, row, col, size);

    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {
        gridBoard.render(batch);
    }

    public void handleInput(float dt) {

    }
}
