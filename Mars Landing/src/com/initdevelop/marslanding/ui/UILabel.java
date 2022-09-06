package com.initdevelop.marslanding.ui;

import java.awt.Graphics;

import com.initdevelop.marslanding.textures.FontTexture;

public class UILabel extends UIControl {
	
	public final static int ALLIGN_LEFT = 0;
	public final static int ALLIGN_CENTER = 1;
	public final static int ALLIGN_RIGHT = 2;
	
	private int allign = 0;
	private boolean isLong = false;
	private char[] chars;
	private int fontHeight = 8;
	
	public UILabel(int x, int y, int width, int height, String text, int allign) {
		this.relx = x;
		this.rely = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.allign = allign;
		
		this.chars = this.text.toUpperCase().toCharArray();
		this.isLong = (chars.length > width / height);
		if (!isLong) {
			this.fontHeight = (int) (this.height * 0.6);
		} else {
			this.fontHeight = (int) (this.width * 0.6 / chars.length);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		if (isLong) {
			for (int i = 0; i < chars.length; i++) {
				g.drawImage(FontTexture.fonts.get(chars[i]),
						(int)((float)relx + (float)width * 0.2 + fontHeight * i),
						(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5),
						(int)(fontHeight),
						(int)(fontHeight),
						null);
			}
		} else {
			if (allign == 0) {
				for (int i = 0; i < chars.length; i++) {
					g.drawImage(FontTexture.fonts.get(chars[i]),
							(int)(relx + height * 0.2) + fontHeight * i,
							(int)(rely + height * 0.2),
							(int)(fontHeight),
							(int)(fontHeight),
							null);
				}
			} else if (allign == 1) {
				for (int i = 0; i < chars.length; i++) {
					g.drawImage(FontTexture.fonts.get(chars[i]),
							(int)((float)relx + (float)width / 2 + fontHeight*(i - chars.length * 0.5)),
							(int)((float)rely + (float)height * 0.2),
							(int)(fontHeight),
							(int)(fontHeight),
							null);
				}
			} else if (allign == 2) {
				for (int i = 0; i < chars.length; i++) {
					g.drawImage(FontTexture.fonts.get(chars[chars.length - i - 1]),
							(int)((float)relx - (float)height * 0.2 + (float)width - fontHeight * (i + 1)),
							(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5),
							(int)(fontHeight),
							(int)(fontHeight),
							null);
				}
			}
		}
	}
}
