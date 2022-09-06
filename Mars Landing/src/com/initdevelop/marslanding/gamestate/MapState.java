package com.initdevelop.marslanding.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import com.initdevelop.marslanding.game.GameState;
import com.initdevelop.marslanding.game.GameStateManager;
import com.initdevelop.marslanding.mars.Camera;
import com.initdevelop.marslanding.mars.WorldGenerator;
import com.initdevelop.marslanding.textures.Textures;
import com.initdevelop.marslanding.ui.UIButton;

public class MapState extends GameState {
	
	public WorldGenerator worldGenerator;
	public Random rand;
	public Camera camera;
	
	public int SEL_X = 0;
	public int SEL_Y = 0;
	
	boolean keyDownW = false;
	boolean keyDownA = false;
	boolean keyDownS = false;
	boolean keyDownD = false;
	
	
	public MapState() {
		rand = new Random();
		this.worldGenerator = new WorldGenerator(rand.nextInt());
		
		camera = new Camera();
		
		this.worldGenerator.generateChunk(0, 0);
		this.worldGenerator.generateChunk(1, 0);
		this.worldGenerator.generateChunk(1, 1);
		this.worldGenerator.generateChunk(0, 1);
		this.worldGenerator.generateChunk(-1, 1);
		this.worldGenerator.generateChunk(-1, 0);
		this.worldGenerator.generateChunk(-1, -1);
		this.worldGenerator.generateChunk(0, -1);
		this.worldGenerator.generateChunk(1, -1);
		
		this.stateName = "TestStateB";
		this.stateID = 0;
		
		this.controls.add(new UIButton(W(28), H(14), W(3), H(3), "Back") {
			@Override
			public void buttonClickEvent(int mouseInput) {
				GameStateManager.setGameState(0);
			}
			
		});
	}
	
	@Override
	public void update() {
		
		if (keyDownW) {
			camera.Y -= camera.cameraSpeed;
		}
		if (keyDownA) {
			camera.X -= camera.cameraSpeed;
		}
		if (keyDownS) {
			camera.Y += camera.cameraSpeed;
		}
		if (keyDownD) {
			camera.X += camera.cameraSpeed;
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		for (List<Integer> tup : worldGenerator.chunks.keySet()) {
			for (int x = 0; x < WorldGenerator.CHUNK_SIZE; x++) {
				for (int y = 0; y < WorldGenerator.CHUNK_SIZE; y++) {
					int frameX = (int)(W(2) * (-camera.X + tup.get(0) * WorldGenerator.CHUNK_SIZE + x));
					int frameY = (int)(W(2) * (-camera.Y + tup.get(1) * WorldGenerator.CHUNK_SIZE  + y));
					
					if (frameX >= W(-2) && frameX < W(34) && frameY >= W(-2) && frameY <= W(34)) {
						int degree = (int)(worldGenerator.chunks.get(tup).heightMap[x][y] / 13);
						g.drawImage(Textures.surfaceTileTextures[degree], frameX, frameY, W(2), W(2), null);
					}
					
					
				}
			}
		}
		super.paintComponents(g);
	}
	
	@Override
	public void mouseClickEvent(int mouseInput, int x, int y) {
		super.mouseClickEvent(mouseInput, x, y);
	}
	
	@Override
	public void mouseMoveEvent(int x, int y) {
		super.mouseMoveEvent(x, y);
		//SEL_X = 
	}
	
	@Override
	public void keyDownEvent(KeyEvent keyInput) {
		super.keyDownEvent(keyInput);
		if (keyInput.getKeyCode() == KeyEvent.VK_W) {
			keyDownW = true;
		}
		if (keyInput.getKeyCode() == KeyEvent.VK_A) {
			keyDownA = true;
		}
		if (keyInput.getKeyCode() == KeyEvent.VK_S) {
			keyDownS = true;
		}
		if (keyInput.getKeyCode() == KeyEvent.VK_D) {
			keyDownD = true;
		}
	}
	
	@Override
	public void keyUpEvent(KeyEvent keyInput) {
		super.keyUpEvent(keyInput);
		if (keyInput.getKeyCode() == KeyEvent.VK_W) {
			keyDownW = false;
		}
		if (keyInput.getKeyCode() == KeyEvent.VK_A) {
			keyDownA = false;
		}
		if (keyInput.getKeyCode() == KeyEvent.VK_S) {
			keyDownS = false;
		}
		if (keyInput.getKeyCode() == KeyEvent.VK_D) {
			keyDownD = false;
		}
	}
	
	@Override
	public void keyPressEvent(KeyEvent keyInput) {
		super.keyPressEvent(keyInput);
		/*
		if (keyInput.getKeyChar() == 'w') {
			camera.Y -= camera.cameraSpeed;
		} else if (keyInput.getKeyChar() == 'a') {
			camera.X -= camera.cameraSpeed;
		} else if (keyInput.getKeyChar() == 's') {
			camera.Y += camera.cameraSpeed;
		} else if (keyInput.getKeyChar() == 'd') {
			camera.X += camera.cameraSpeed;
		}
		*/
	}
	
	
	
	
}
