package ca.codemake.CubeBreak.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void setState(State state) {
        states.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }
}
