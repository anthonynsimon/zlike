package com.anthonynsimon.zlike.desktop;

import com.anthonynsimon.zlike.Globals;
import com.anthonynsimon.zlike.ZLikeGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Globals.targetWidth * 2;
		config.height = Globals.targetHeight * 2;
		config.resizable = true;
		config.title = "zlike";
		config.foregroundFPS = 60;
		config.vSyncEnabled = true;
		config.samples = 0;
		config.fullscreen = false;

		new LwjglApplication(new ZLikeGame(), config);
	}
}
