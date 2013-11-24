package com.virtualfactory.pathfinding.heuristics;

import com.virtualfactory.pathfinding.AStarHeuristic;
import com.virtualfactory.pathfinding.TileMap;

/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 * 
 * @author Kevin Glass
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileMap, Mover, int, int, int, int)
	 */
        @Override
	public float getCost(TileMap map, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}

}
