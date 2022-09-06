package com.initdevelop.marslanding.gamestate;

import java.awt.Color;
import java.awt.Graphics;

import com.initdevelop.marslanding.game.GameScreen;
import com.initdevelop.marslanding.game.GameState;
import com.initdevelop.marslanding.game.GameStateManager;
import com.initdevelop.marslanding.textures.Textures;
import com.initdevelop.marslanding.ui.UIButton;
import com.initdevelop.marslanding.ui.UILabel;
import com.initdevelop.marslanding.ui.UITextBox;

public class LoginState extends GameState {
	
	public LoginState() {
		this.stateName = "TestStateA";
		this.stateID = 0;
		
		this.controls.add(new UIButton(BW * 19, BH * 15,
				BW * 9, BH * 2, "LOGIN") {
			@Override
			public void buttonClickEvent(int mouseInput) {
				GameStateManager.setGameState(1);
			}
		});
		
		this.controls.add(new UITextBox(BW * 4, BH * 15,
				BW * 14, BH * 2, ""));
		
		this.controls.add(new UILabel(W(2), H(3), W(28), H(5), "UNNAMED GAME!", UILabel.ALLIGN_CENTER));
		
	}
	
	@Override
	public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(Textures.menuBackground, 0, 0, 
        		GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT, null);
        //g.drawImage(FontTexture.fonts.get('A'), 100, 200, 300, 400, null);
        //g.drawString("Hello World", 500, 700);
        this.paintComponents(g);
	}
	
	
	@Override
	public void mouseClickEvent(int mouseInput, int x, int y) {
		super.mouseClickEvent(mouseInput, x, y);
	}
	

	
}
