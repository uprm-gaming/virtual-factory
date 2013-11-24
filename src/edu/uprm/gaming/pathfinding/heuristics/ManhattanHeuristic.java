package edu.uprm.gaming.pathfinding.heuristics;

import edu.uprm.gaming.pathfinding.AStarHeuristic;
import edu.uprm.gaming.pathfinding.TileMap;

/**
 * A heuristic that drives the search based on the Manhattan distance
 * between the current location and the target
 * 
 * @author Kevin Glass
 */
public class ManhattanHeuristic implements AStarHeuristic {
	/** The minimum movement cost from any one square to the next */
	private int minimumCost;
	
	/**
	 * Create a new heuristic 
	 * 
	 * @param minimumCost The minimum movement cost from any one square to the next
	 */
	public ManhattanHeuristic(int minimumCost) {
		this.minimumCost = minimumCost;
	}
	
	/**
	 * @see AStarHeuristic#getCost(TileMap, Mover, int, int, int, int)
	 */
        @Override
	public float getCost(TileMap map, int x, int y, int tx,
			int ty) {
		return minimumCost * (Math.abs(x-tx) + Math.abs(y-ty));
	}

}
