package com.initdevelop.marslanding.textures;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Textures {

	public static Image menuBackground;
	
	public static Image[] surfaceTileTextures;
	
	public Textures() {
		menuBackground = (new ImageIcon("res/textures/background/menu_background.png")).getImage();
		
		surfaceTileTextures = new Image[10];
		
		for (int i = 0; i < 10; i++) {
			surfaceTileTextures[i] = (new ImageIcon("res/textures/tile/surface" + i + ".png")).getImage();
		}
	}
	
}
