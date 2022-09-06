package com.initdevelop.marslanding.ui;

import java.awt.Color;
import java.awt.Graphics;
import com.initdevelop.marslanding.textures.FontTexture;

public class UIButton extends UIControl {
	
	boolean isPressed = false;
	boolean isHovered = false;
	boolean isLong = false;
	float charHeight = 0;
	char chars[];
	
	final Color defaultColor = new Color(0, 13, 91, 128);
	final Color hoverColor = new Color(0, 88, 91, 128);
	final Color pressedColor = new Color(0, 0, 0, 128);
	
	public UIButton(int x, int y, int width, int height, String text) {
		this.relx = x;
		this.rely = y;
		this.width = width;
		this.height = height;
		this.text = text;
		updateChars();
	}
	
	public void updateChars() {
		this.chars = this.text.toUpperCase().toCharArray();
		this.isLong = (chars.length > width / height);
		if (!isLong) {
			this.charHeight = (float) (this.height * 0.6);
		} else {
			this.charHeight = (float) (this.width * 0.6 / chars.length);
		}
	}
	
	@Override
	public void mouseDownEvent(int mouseInput, int x, int y) {
		if (this.relx + width >= x && x >= this.relx) {
			if (this.rely + height >= y && y >= this.rely) {
				buttonDownEvent(mouseInput);
				isPressed = true;
			}
		}
	}

	@Override
	public void mouseUpEvent(int mouseInput, int x, int y) {
		if (this.relx + width >= x && x >= this.relx) {
			if (this.rely + height >= y && y >= this.rely) {
				buttonUpEvent(mouseInput);
				isPressed = false;
				isHovered = false;
			}
		}
	}
	
	@Override
	public void mouseClickEvent(int mouseInput, int x, int y) {
		if (this.relx + width >= x && x >= this.relx) {
			if (this.rely + height >= y && y >= this.rely) {
				buttonClickEvent(mouseInput);
			}
		}
	}
	
	@Override
	public void mouseMoveEvent(int x, int y) {
		if (this.relx + width >= x && x >= this.relx && this.rely + height >= y && y >= this.rely) {
			buttonMoveEvent();
			isHovered = true;
		} else {
			isHovered = false;
			isPressed = false;
		}
	}

	@Override
	public void paint(Graphics g) {
		if (isPressed) {
			g.setColor(pressedColor);
		} else {
			if (isHovered) {
				g.setColor(hoverColor);
			} else {
				g.setColor(defaultColor);
			}
		}
		g.fillRect(relx, rely, width, height);
		
		if (!isLong) {
			for (int i = 0; i < chars.length; i++) {
				g.drawImage(FontTexture.fonts.get(chars[i]),
						(int)((float)relx + (float)width / 2 + charHeight*(i - chars.length * 0.5)),
						(int)((float)rely + (float)height * 0.2),
						(int)(charHeight),
						(int)(charHeight),
						null);
			}
		} else {
			for (int i = 0; i < chars.length; i++) {
				g.drawImage(FontTexture.fonts.get(chars[i]),
						(int)((float)relx + (float)width * 0.2 + charHeight * i),
						(int)((float)rely + (float)height * 0.5 - charHeight * 0.5),
						(int)(charHeight),
						(int)(charHeight),
						null);
			}
		}
	}
	
	public void buttonDownEvent(int mouseInput) {}
	public void buttonUpEvent(int mouseInput) {}
	public void buttonClickEvent(int mouseInput) {}
	public void buttonMoveEvent() {}

	
}
