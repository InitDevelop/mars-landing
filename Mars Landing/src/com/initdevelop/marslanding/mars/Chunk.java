package com.initdevelop.marslanding.mars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class Chunk {

	public byte[][] heightMap;
	
	public Chunk() {
		heightMap = new byte[WorldGenerator.CHUNK_SIZE][WorldGenerator.CHUNK_SIZE];
	}
	
	public void createMainChunk(HashMap<List<Integer>, Byte> raiseMap) {
		for (int x = 0; x < WorldGenerator.CHUNK_SIZE; x++) {
			for (int y = 0; y < WorldGenerator.CHUNK_SIZE; y++) {
				List<Integer> thisTuple = Arrays.asList(x, y);
				if (raiseMap.containsKey(thisTuple)) {
					heightMap[x][y] = raiseMap.get(thisTuple);
				} else {
					heightMap[x][y] = 0;
				}
			}
		}
	}
	
	public void mergeOverlapChunk(HashMap<List<Integer>, Byte> raiseMap, byte direction) {
		HashMap<List<Integer>, Byte> convertedOverlapMap = new HashMap<List<Integer>, Byte>();
		switch (direction) {
		case OverlappingChunk.EAST:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0) - WorldGenerator.CHUNK_SIZE, tup.get(1))
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.NORTH_EAST:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0) - WorldGenerator.CHUNK_SIZE, tup.get(1) + WorldGenerator.CHUNK_SIZE)
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.NORTH:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0), tup.get(1) + WorldGenerator.CHUNK_SIZE)
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.NORTH_WEST:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0) + WorldGenerator.CHUNK_SIZE, tup.get(1) + WorldGenerator.CHUNK_SIZE)
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.WEST:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0) + WorldGenerator.CHUNK_SIZE, tup.get(1))
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.SOUTH_WEST:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0) + WorldGenerator.CHUNK_SIZE, tup.get(1) - WorldGenerator.CHUNK_SIZE)
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.SOUTH:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0), tup.get(1) - WorldGenerator.CHUNK_SIZE)
						, raiseMap.get(tup));
			}
			break;
		case OverlappingChunk.SOUTH_EAST:
			for (List<Integer> tup : raiseMap.keySet()) {
				convertedOverlapMap.put(
						Arrays.asList(tup.get(0) - WorldGenerator.CHUNK_SIZE, tup.get(1) - WorldGenerator.CHUNK_SIZE)
						, raiseMap.get(tup));
			}
			break;
		}
		for (List<Integer> tup : convertedOverlapMap.keySet()) {
			heightMap[tup.get(0)][tup.get(1)] = 
					(byte)Math.max(convertedOverlapMap.get(tup),
							heightMap[tup.get(0)][tup.get(1)]);
		}
	}
	
}
