package com.anthonynsimon.zlike.desktop;

import com.anthonynsimon.zlike.GameSettings;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anthonynsimon.zlike.ZLikeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = GameSettings.desktopWidth;
		config.height = GameSettings.desktopHeight;
		config.resizable = GameSettings.resizable;
		config.title = GameSettings.title;
		config.foregroundFPS = GameSettings.targetFps;

		new LwjglApplication(new ZLikeGame(), config);
	}
}
