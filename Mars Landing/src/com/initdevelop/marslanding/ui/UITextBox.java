package com.initdevelop.marslanding.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import com.initdevelop.marslanding.textures.FontTexture;

public class UITextBox extends UIControl {

	final Color defaultColor = new Color(0, 0, 0, 128);
	final Color hoverColor = new Color(0, 0, 0, 64);
	final Color pressedColor = new Color(0, 0, 0, 200);
	final Color cursorYellow = new Color(249, 236, 159);
	
	public boolean isActive = false;
	boolean isPressed = false;
	boolean isHovered = false;
	boolean isOverflow = false;
	public int fontHeight = 8;
	
	char chars[];
	
	public UITextBox(int x, int y, int width, int height, String text) {
		this.relx = x;
		this.rely = y;
		this.width = width;
		this.height = height;
		this.text = text;
		updateChars();
	}
	
	public void updateChars() {
		this.chars = this.text.toUpperCase().toCharArray();
		fontHeight = (int) (height * 0.6);
		if ((float)(text.length() * fontHeight) + (float)(height * 0.4) > width) {
			isOverflow = true;
		} else {
			isOverflow = false;
		}
	}
	
	@Override
	public void mouseDownEvent(int mouseInput, int x, int y) {
		if (this.relx + width >= x && x >= this.relx) {
			if (this.rely + height >= y && y >= this.rely) {
				isPressed = true;
			}
		}
	}

	@Override
	public void mouseUpEvent(int mouseInput, int x, int y) {
		if (this.relx + width >= x && x >= this.relx) {
			if (this.rely + height >= y && y >= this.rely) {
				isPressed = false;
				isHovered = false;
			}
		}
	}
	
	@Override
	public void mouseClickEvent(int mouseInput, int x, int y) {
		if (this.relx + width >= x && x >= this.relx) {
			if (this.rely + height >= y && y >= this.rely) {
				isActive = true;
			} else {
				isActive = false;
			}
		} else {
			isActive = false;
		}
	}
	
	@Override
	public void mouseMoveEvent(int x, int y) {
		if (this.relx + width >= x && x >= this.relx && this.rely + height >= y && y >= this.rely) {
			isHovered = true;
		} else {
			isHovered = false;
			isPressed = false;
		}
	}
	
	public void keyPressEvent(KeyEvent keyInput) {
		
		if (isActive) {
			if (keyInput.getKeyChar() == '\u0008') {
				if (this.text.length() > 0)
					this.text = this.text.substring(0, this.text.length() - 1);
			} else if (keyInput.getKeyChar() == '\n') {
				// Ignore
			} else if (FontTexture.fonts.containsKey(keyInput.getKeyChar())) {
				this.text += keyInput.getKeyChar();
			}
			updateChars();
		}

	}
	
	
	@Override
	public void paint(Graphics g) {
		
		if (isActive) {
			g.setColor(pressedColor);
		} else {
			if (isHovered) {
				g.setColor(hoverColor);
			} else {
				g.setColor(defaultColor);
			}
		}
		
		g.fillRect(relx, rely, width, height);
		

		if (isOverflow) {
			for (int i = 0; i < (int)((width - 0.4 * height)/fontHeight); i++) {
				g.drawImage(FontTexture.fonts.get(chars[chars.length - i - 1]),
						(int)((float)relx - (float)height * 0.2 + (float)width - fontHeight * (i + 1)),
						(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5),
						(int)(fontHeight),
						(int)(fontHeight),
						null);
			}
			
			if (isActive) {
				g.setColor(cursorYellow);
				g.fillRect(relx + width - (int)(height * 0.15),
						(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5), (int)(height * 0.1), fontHeight);
				}
			
			
			
			//int leftover = (int)(8 * (width - 0.2 * height - cnt * fontHeight) / fontHeight);
			/*
			g.drawImage(FontTexture.fonts.get(
					chars[chars.length - (int)(width - 0.2 * height)/fontHeight - 1]
							).getSubimage(8 - leftover, 8, leftover, 8),
					(int)((float)relx + (float)height * 0.1),
					(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5),
					(int)(fontHeight),
					(int)(fontHeight),
					null);
					*/
		} else {
			for (int i = 0; i < chars.length; i++) {
				g.drawImage(FontTexture.fonts.get(chars[i]),
						(int)((float)relx + (float)height * 0.2 + fontHeight * i),
						(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5),
						(int)(fontHeight),
						(int)(fontHeight),
						null);
			}
			if (isActive) {
				g.setColor(cursorYellow);
				g.fillRect((int)((float)relx + (float)height * 0.2 + fontHeight * chars.length) + (int)(height * 0.05),
						(int)((float)rely + (float)height * 0.5 - fontHeight * 0.5), (int)(height * 0.1), fontHeight);
				}
			}
		
	}
	
}
