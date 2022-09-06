package com.initdevelop.marslanding.textures;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import com.initdevelop.marslanding.game.IO;

public class FontTexture {

	public static HashMap<Character, BufferedImage> fonts;
	
	public final String[] fontIndex
		= {"ABCDEFGHIJKLMNOP",
				"QRSTUVWXYZ",
				"0123456789",
				" !\"#$%&'()*+,-./",
				":;<=>?"};
	
	public FontTexture() {
		
		BufferedImage fontImg;
		fonts = new HashMap<Character, BufferedImage>();
		
		try {
			fontImg = ImageIO.read(getClass().getResource("font.png"));
			for (int i = 0; i < fontIndex.length; i++) {
				for (int j = 0; j < fontIndex[i].length(); j++) {
					fonts.put(fontIndex[i].substring(j, j + 1).toCharArray()[0],
							fontImg.getSubimage(j * 8, i * 8, 8, 8));
				}
			}
			IO.print("Game font loading was successful.");
		} catch (IOException e) {
			e.printStackTrace();
			IO.print("Font image loading failed.");
		}
		
		
		
		
	}
	
}
