package com.initdevelop.marslanding.mars;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OverlappingChunk {
	
	final static byte EAST = 0;
	final static byte NORTH_EAST = 1;
	final static byte NORTH = 2;
	final static byte NORTH_WEST = 3;
	final static byte WEST = 4;
	final static byte SOUTH_WEST = 5;
	final static byte SOUTH = 6;
	final static byte SOUTH_EAST = 7;

	
	int seed;
	public HashMap<List<Integer>, Byte> raiseMap;
	
	
	// IntTuple key is relevant to the left-top-most point of the chunk,
	// regardless of the chunk's absolute location.
	
	public OverlappingChunk(int seed, List<Integer> chunkLocations) {
		raiseMap = new HashMap<List<Integer>, Byte>();
		this.seed = seed;
		
	}
	
	public void generateRaiseMap() {
		
		Random rand = new Random(seed);
		
		ArrayList<Point> hillTops = new ArrayList<Point>();
		
		int numberHills = WorldGenerator.MAX_HILL_COUNT;
		
		for (int i = 0; i < numberHills; i++) {
			int hx = rand.nextInt(0, WorldGenerator.CHUNK_SIZE);
			int hy = rand.nextInt(0, WorldGenerator.CHUNK_SIZE);
			if (!hillTops.contains(new Point(hx, hy))) {
				hillTops.add(new Point(hx, hy));
			}
		}
		
		int randomCheck = 0;
		
		for (Point p : hillTops) {
			for (int n = 0; n < WorldGenerator.RANDOM_WALKER_COUNT; n++) {
				int currentX = p.x;
				int currentY = p.y;
				for (int nw = 0; nw < WorldGenerator.RANDOM_WALK_LENGTH; nw++) {
					if (!raiseMap.containsKey(Arrays.asList(currentX, currentY))) {
							raiseMap.put(Arrays.asList(currentX, currentY), (byte) rand.nextDouble(
									(WorldGenerator.RANDOM_WALK_LENGTH - nw) * WorldGenerator.HILL_HEIGHT / WorldGenerator.RANDOM_WALK_LENGTH,
									(WorldGenerator.RANDOM_WALK_LENGTH + 1 - nw) * WorldGenerator.HILL_HEIGHT / WorldGenerator.RANDOM_WALK_LENGTH));
					}
					randomCheck = rand.nextInt(0, 100);
					if (randomCheck <= rand.nextDouble(n * (100 / WorldGenerator.RANDOM_WALKER_COUNT),
							(n + 1) * (100 / WorldGenerator.RANDOM_WALKER_COUNT))) {
						currentX += rand.nextInt(0, 2) * 2 - 1;
					} else {
						currentY += rand.nextInt(0, 2) * 2 - 1;
					}
				}
			}
		}
		
		
		// ERODE
		
		for (int i = 0; i < WorldGenerator.ERODE_CONSTANT; i++) {
			for (int cx = -WorldGenerator.CHUNK_SIZE; cx < 2*WorldGenerator.CHUNK_SIZE - 1; cx++) {
				for (int cy = -WorldGenerator.CHUNK_SIZE; cy < 2* WorldGenerator.CHUNK_SIZE - 1; cy++) {
					List<Integer> thisTuple = Arrays.asList(cx, cy);
					List<Integer> rightTuple = Arrays.asList(cx + 1, cy);
					List<Integer> downTuple = Arrays.asList(cx, cy + 1);
					
					if (raiseMap.containsKey(thisTuple)) {
						// Comparing with Right Tuple
						if (raiseMap.containsKey(rightTuple)) {
							if (Math.abs(raiseMap.get(thisTuple) - raiseMap.get(rightTuple)) > WorldGenerator.ERODE_SENSITIVITY) {
								if (raiseMap.get(thisTuple) > raiseMap.get(rightTuple)) {
									raiseMap.put(rightTuple, (byte)((double)raiseMap.get(rightTuple) * (1 - WorldGenerator.SMOOTH_CONSTANT)
											+ (double)raiseMap.get(thisTuple) * WorldGenerator.SMOOTH_CONSTANT));
								} else {
									raiseMap.put(thisTuple, (byte)((double)raiseMap.get(thisTuple) * (1 - WorldGenerator.SMOOTH_CONSTANT)
											+ (double)raiseMap.get(rightTuple) * WorldGenerator.SMOOTH_CONSTANT));
								}
							}
						} else {
							if (WorldGenerator.ERODE_SENSITIVITY < raiseMap.get(thisTuple)) {
								raiseMap.put(rightTuple, (byte)(raiseMap.get(thisTuple) * WorldGenerator.SMOOTH_CONSTANT));
							}
						}
						
						// Comparing with Down Tuple
						if (raiseMap.containsKey(downTuple)) {
							if (Math.abs(raiseMap.get(thisTuple) - raiseMap.get(downTuple)) > WorldGenerator.ERODE_SENSITIVITY) {
								if (raiseMap.get(thisTuple) > raiseMap.get(downTuple)) {
									raiseMap.put(downTuple, (byte)((double)raiseMap.get(downTuple) * (1 - WorldGenerator.SMOOTH_CONSTANT)
											+ (double)raiseMap.get(thisTuple) * WorldGenerator.SMOOTH_CONSTANT));
								} else {
									raiseMap.put(thisTuple, (byte)((double)raiseMap.get(thisTuple) * (1 - WorldGenerator.SMOOTH_CONSTANT)
											+ (double)raiseMap.get(downTuple) * WorldGenerator.SMOOTH_CONSTANT));
								}
							}
						} else {
							if (WorldGenerator.ERODE_SENSITIVITY < raiseMap.get(thisTuple)) {
								raiseMap.put(downTuple, (byte)(raiseMap.get(thisTuple) * WorldGenerator.SMOOTH_CONSTANT));
							}
						}
					} else {
						// Comparing with Right Tuple
						if (raiseMap.containsKey(rightTuple)) {
							if (WorldGenerator.ERODE_SENSITIVITY < raiseMap.get(rightTuple)) {
								raiseMap.put(thisTuple, (byte)(raiseMap.get(rightTuple) * WorldGenerator.SMOOTH_CONSTANT));
							}
						} else {
							// Ignore
						}
						
						// Comparing with Down Tuple
						if (raiseMap.containsKey(downTuple)) {
							if (WorldGenerator.ERODE_SENSITIVITY < raiseMap.get(downTuple)) {
								raiseMap.put(thisTuple, (byte)(raiseMap.get(downTuple) * WorldGenerator.SMOOTH_CONSTANT));
							}
						} else {
							// Ignore
						}
					}
				}
			}
		}
				

		int MAX = -1;
		int MIN =  128;
		
		for (Map.Entry<List<Integer>, Byte> entry : raiseMap.entrySet()) {
			if (entry.getValue() > MAX) {
				MAX = entry.getValue();
			}
			if (entry.getValue() < MIN) {
				MIN = entry.getValue();
			}
		}
		
		int INTERVAL = (MAX - MIN);
		
		for (List<Integer> tup : raiseMap.keySet()) {
			raiseMap.put(tup, (byte)((raiseMap.get(tup) - MIN) * 100 / INTERVAL));
		}

	}

	public HashMap<List<Integer>, Byte> getInRange() {
		HashMap<List<Integer>, Byte> returnMap = new HashMap<List<Integer>, Byte>();
		for (List<Integer> tup : raiseMap.keySet()) {
			if (tup.get(0) >= 0 && tup.get(0) < WorldGenerator.CHUNK_SIZE
					&& tup.get(1) >= 0 && tup.get(1) < WorldGenerator.CHUNK_SIZE) {
				returnMap.put(tup, raiseMap.get(tup));
			}
		}
		return returnMap;
	}
	
	public HashMap<List<Integer>, Byte> getOverlap(byte direction) {
		HashMap<List<Integer>, Byte> returnMap = new HashMap<List<Integer>, Byte>();
		switch (direction) {
		case EAST:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(0) >= WorldGenerator.CHUNK_SIZE && tup.get(0) < 2 * WorldGenerator.CHUNK_SIZE
						&& tup.get(1) >= 0 && tup.get(1) < WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case NORTH_EAST:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(0) >= WorldGenerator.CHUNK_SIZE && tup.get(0) < 2 * WorldGenerator.CHUNK_SIZE
						&& tup.get(1) < 0 && tup.get(1) >= -WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case NORTH:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(1) < 0 && tup.get(1) >= -WorldGenerator.CHUNK_SIZE
						&& tup.get(0) >= 0 && tup.get(0) < WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case NORTH_WEST:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(0) < 0 && tup.get(0) >= -WorldGenerator.CHUNK_SIZE
						&& tup.get(1) < 0 && tup.get(1) >= -WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case WEST:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(0) < 0 && tup.get(0) >= -WorldGenerator.CHUNK_SIZE
						&& tup.get(1) >= 0 && tup.get(1) < WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case SOUTH_WEST:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(0) < 0 && tup.get(0) >= -WorldGenerator.CHUNK_SIZE
						&& tup.get(1) >= WorldGenerator.CHUNK_SIZE && tup.get(1) < 2 * WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case SOUTH:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(1) >= WorldGenerator.CHUNK_SIZE && tup.get(1) < 2 * WorldGenerator.CHUNK_SIZE
						&& tup.get(0) >= 0 && tup.get(0) < WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		case SOUTH_EAST:
			for (List<Integer> tup : raiseMap.keySet()) {
				if (tup.get(0) >= WorldGenerator.CHUNK_SIZE && tup.get(0) < 2 * WorldGenerator.CHUNK_SIZE
						&& tup.get(1) >= WorldGenerator.CHUNK_SIZE && tup.get(1) < 2 * WorldGenerator.CHUNK_SIZE) {
					returnMap.put(tup, raiseMap.get(tup));
				}
			}
			break;
		}
		return returnMap;
	}
	
}
