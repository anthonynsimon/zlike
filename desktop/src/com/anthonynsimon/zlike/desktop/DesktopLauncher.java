package com.anthonynsimon.zlike.desktop;

import com.anthonynsimon.zlike.GameConf;
import com.anthonynsimon.zlike.GameMain;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = GameConf.V_WIDTH * 3;
		config.height = GameConf.V_HEIGHT * 3;
		config.resizable = true;
		config.title = "zlike";
		config.foregroundFPS = 60;
		config.vSyncEnabled = true;
		config.samples = 0;
		config.fullscreen = false;

		new LwjglApplication(new GameMain(), config);
	}
}
