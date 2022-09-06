package com.initdevelop.marslanding.mars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class WorldGenerator {

	public static final double SMOOTH_CONSTANT = 0.6;
	public static final double HILL_HEIGHT = 30.0;
	public static final int ERODE_CONSTANT = 5;
	public static final int ERODE_SENSITIVITY = 7;
	public static final int PLAIN_LOW = 10;
	public static final int PLAIN_HIGH = 30;
	public static final int MAX_HILL_COUNT = 6;
	public static final int RANDOM_WALKER_COUNT = 17;
	public static final int RANDOM_WALK_LENGTH = 111;
	public static final int CHUNK_SIZE = 64;
	
	public int seed;
	
	public HashMap<List<Integer>, OverlappingChunk> overlappingChunks;
	public HashMap<List<Integer>, Chunk> chunks;
	public Random rand;
	
	public WorldGenerator(int seed) {
		overlappingChunks = new HashMap<List<Integer>, OverlappingChunk>();
		chunks = new HashMap<List<Integer>, Chunk>();
		this.seed = seed;
		rand = new Random(seed);
	}
	
	public void generateChunk(int chunkLocX, int chunkLocY) {
		Chunk chunk = new Chunk();
		
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				List<Integer> vec = Arrays.asList(chunkLocX + x, chunkLocY + y);
				if (!overlappingChunks.containsKey(vec)) {
					//IO.print(String.valueOf(tup.getX()) + ", " + String.valueOf(tup.getY()));
					OverlappingChunk oChunk = new OverlappingChunk(rand.nextInt(),
							Arrays.asList(chunkLocX + x, chunkLocY + y));
					oChunk.generateRaiseMap();
					
					overlappingChunks.put(vec, oChunk);
				}
				
			}
		}
		
		//IO.print(String.valueOf(overlappingChunks.containsKey(Arrays.asList(chunkLocX, chunkLocY))));
		
		chunk.createMainChunk(overlappingChunks.get(Arrays.asList(chunkLocX, chunkLocY)).getInRange());
		
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				List<Integer> tup = Arrays.asList(chunkLocX + x, chunkLocY + y);
				if (x == 0 && y == 0) {
					// Ignore
				} else {
					byte direction = 0;
					if (x == -1) {
						if (y == -1) {
							direction = OverlappingChunk.SOUTH_EAST;
						} else if (y == 0) {
							direction = OverlappingChunk.EAST;
						} else if (y == 1) {
							direction = OverlappingChunk.NORTH_EAST;
						}
					} else if (x == 0) {
						if (y == -1) {
							direction = OverlappingChunk.SOUTH;
						} else if (y == 1) {
							direction = OverlappingChunk.NORTH;
						}
					} else if (x == 1) {
						if (y == -1) {
							direction = OverlappingChunk.SOUTH_WEST;
						} else if (y == 0) {
							direction = OverlappingChunk.WEST;
						} else if (y == 1) {
							direction = OverlappingChunk.NORTH_WEST;
						}
					}
					chunk.mergeOverlapChunk(overlappingChunks.get(tup).getOverlap(direction), direction);
				}
			}
		}
		chunks.put(Arrays.asList(chunkLocX, chunkLocY), chunk);
	}
	
}
