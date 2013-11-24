package edu.uprm.gaming.pathfinding.heuristics;

import edu.uprm.gaming.pathfinding.AStarHeuristic;
import edu.uprm.gaming.pathfinding.TileMap;

/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile. In this case the sqrt is removed
 * and the distance squared is used instead
 * 
 * @author Kevin Glass
 */
public class ClosestSquaredHeuristic implements AStarHeuristic {

	/**
	 * @see AStarHeuristic#getCost(TileMap, Mover, int, int, int, int)
	 */
        @Override
	public float getCost(TileMap map, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		return ((dx*dx)+(dy*dy));
	}

}
