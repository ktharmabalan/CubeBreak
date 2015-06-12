package ca.codemake.CubeBreak.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.codemake.CubeBreak.helpers.Assets;
import javafx.geometry.Pos;

/**
 * Created by Kajan on 4/29/2015.
 */
public class GridTile extends AbstractGameObject {

    private int row;
    private int col;

    private int color;
    private boolean selected;
    private boolean checked;

//    public Vector2 target;
    public int targetColor;

    // Move
    public boolean moving;
    private float moveSpeed;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

    public Position original;
    public Position target;

    class Position {
        public int row;
        public int col;
        public float x;
        public float y;
        public int color;

        public Position(int row, int col, float x, float y, int color) {
            this.row = row;
            this.col = col;
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public GridTile(int color, float x, float y, float width, float height, int col, int row) {
        init(color, x, y, width, height, col, row);
    }

    private void init(int color, float x, float y, float width, float height, int col, int row) {
        this.color = color;
        dimension.set(width, height);
        position.set(x, y);

//        target = new Vector2();
//        target.set(x, y);

//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
        this.row = row;
        this.col = col;

        targetColor = this.color;
        moving = false;
        moveSpeed = 10;

        original = new Position(getRow(), getCol(), getX(), getY(), color);
        target = new Position(getRow(), getCol(), getX(), getY(), color);
    }

    public void update(float dt) {
        if(target.x > position.x) moveRight = true;
        if(target.x < position.x) moveLeft = true;
        if(target.y > position.y) moveUp = true;
        if(target.y < position.y) moveDown = true;

        if(moveRight || moveLeft || moveUp || moveDown)
//            System.out.println("Moving from: " + position.x + "," + position.y + " to: " + target.x + "," + target.y);

        if(moveLeft) moveLeft();
        else if(moveRight) moveRight();
        else if(moveUp) moveUp();
        else if(moveDown) moveDown();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        drawTile(batch);
//        if (row != 0)
        if(getColor() != 0)
            batch.draw(Assets.instance.dividers.darkbar16, getX() + 1, getY(), getWidth() - 2, 1);
//        if (col != 0)
        if(getColor() != 0)
            batch.draw(Assets.instance.dividers.lightbar16, getX(), getY() + 1, 1, getHeight() - 2);
        batch.end();
    }

    private void drawTile(SpriteBatch batch) {
        if(getDrawColor() != 0)
//            batch.draw(Assets.instance.tiles.lightgrey, getX(), getY(), getWidth(), getHeight());
        if (getDrawColor() == 1) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());

            batch.draw(Assets.instance.tiles.red, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 2) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.green, getX(), getY(), getWidth(), getHeight());
//            batch.setColor(0, 0, 0, 0.25f);
//            batch.draw(alpha, getX(), getY(), getWidth(), getHeight());
//            batch.setColor(1, 1, 1, 1);
        } else if (getDrawColor() == 3) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.blue, getX(), getY(), getWidth(), getHeight());
//            batch.draw(largeglow, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 4) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.black, getX(), getY(), getWidth(), getHeight());
        } else if (getDrawColor() == 0) {
//            batch.draw(select, getX(), getY(), getWidth(), getHeight());
            batch.draw(Assets.instance.tiles.nothing, getX(), getY(), getWidth(), getHeight());
        }
    }

    private void moveLeft() {
        position.x -= moveSpeed;
        if(getX() <= getTargetX()) {
            changeToTarget(true);
            moveLeft = false;
        }
    }

    private void moveRight() {
        position.x += moveSpeed;
        if(getX() >= getTargetX()) {
            changeToTarget(true);
            moveRight = false;
        }
    }

    private void moveUp() {
        position.y += moveSpeed;
        if(getY() >= getTargetY()) {
            changeToTarget(false);
            moveUp = false;
        }
    }

    private void moveDown() {
        position.y -= moveSpeed;
        if(getY() <= getTargetY()) {
            changeToTarget(false);
            moveDown = false;
        }
    }

    // Getters
    private int getDrawColor() {
        return color;
    }
    public int getColor() { return getDrawColor(); }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean getSelected() { return selected; }
    public boolean getChecked() { return checked; }
    public float getTargetX() { return target.x; }
    public float getTargetY() { return target.y; }

//    public Vector2 getTarget() { return target; }

    public int getTargetColor() { return targetColor; }

    public void getDetails() {
        System.out.println("Color: " + color);
//        System.out.println("X: " + getX());
//        System.out.println("Y: " + getY());
//        System.out.println("Width: " + getWidth());
//        System.out.println("Height: " + getHeight());
        System.out.println("Row: " + getRow());
        System.out.println("Col: " + getCol());
//        System.out.println("Selected: " + selected);
//        System.out.println("Checked: " + checked);
        System.out.println("Target.x: " + target.x);
        System.out.println("Target.y: " + target.y);
    }

    public void getTargetDetails() {
        System.out.println("Row: " + getRow());
        System.out.println("Col: " + getCol());
        System.out.println("Target Row: " + target.row);
        System.out.println("Target Col: " + target.col);

//        System.out.println("X: " + getX());
//        System.out.println("Y: " + getY());
//        System.out.println("Target X: " + target.x);
//        System.out.println("Target Y: " + target.y);
    }

    // Setters
    public void setColor(int color) { this.color = color; }
    public void setRow(int row) { this.row = row; }
    public void setCol(int col) { this.col = col; }
    public void setSelected(boolean selected) { this.selected = selected; }
    public void setChecked(boolean checked) { this.checked = checked; }
    public void setTargetX(float x) { target.x = x; }
    public void setTargetY(float y) { target.y = y; }
    public void setTargetRow(int row) {
        target.row = row;
    }
    public void setTargetCol(int col) {
        target.col = col;
    }

//    public void setTarget(Vector2 target) { this.target = target; }

    public void setTargetColor(int color) { this.targetColor = color; }

    public void changeToTarget(boolean horizontal) {
//        System.out.print(col + "," + row + "==>");
        if(horizontal) {
//            System.out.println("change col and target.col");
//            System.out.println("Before: " + getCol() + "=>" + target.col);
            setX(target.x);
            position.x = target.x = original.x;

//            setCol(target.col);
//            System.out.println("After: " + getCol() + "=>" + target.col);
//        target.col = getCol();
//        target.x = getX();
        } else {
//            System.out.println("change row and target.row");
//            System.out.println("Before: " + getRow() + "=>" + target.row);
            setY(target.y);
            position.y = target.y = original.y;

//            setRow(target.row);
//            System.out.println("After: " + getRow() + "=>" + target.row);
//        target.row = getRow();
//        target.y = getY();
        }
//        System.out.println(col + "," + row);
        this.color = original.color = target.color;
    }
}
