package ca.codemake.CubeBreak.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ca.codemake.CubeBreak.Constants;
import ca.codemake.CubeBreak.CubeBreak;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Constants.TITLE;
        config.width = Constants.DESKTOP_WIDTH;
        config.height = Constants.DESKTOP_HEIGHT;
		new LwjglApplication(new CubeBreak(), config);
	}
}
