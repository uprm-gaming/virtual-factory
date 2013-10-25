/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic;

//import edu.uprm.gaming.pathfinding.Mover;
import edu.uprm.gaming.pathfinding.TileMap;
import edu.uprm.gaming.utils.Params;

/**
 *
 * @author David
 */
public class TerrainMap implements TileMap {
    private int[][] tiles = new int[Params.terrainWidth][Params.terrainHeight];
    private int[][] units = new int[Params.terrainWidth][Params.terrainHeight];
    private int[][] costs = new int[Params.terrainWidth][Params.terrainHeight];
    private boolean[][] visited = new boolean[Params.terrainWidth][Params.terrainHeight];
    
    public TerrainMap() {

    }
    
    public void clearVisited() {
        for (int x = 0; x < getWidthInTiles(); x++) {
            for (int y = 0; y < getHeightInTiles(); y++) {
                visited[x][y] = false;
            }
        }
    }

    public boolean visited(int x, int y) {
        return visited[x][y];
    }
    
    public boolean blocked(int x, int y) {
        // if theres a unit at the location, then it's blocked
        if (getUnit(x, y) != 0) {
            return true;
        }
        return false;
    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }
    
    public float getCost(int sx, int sy, int tx, int ty) {
        return costs[sx][sy];
    }

    public int getHeightInTiles() {
        return Params.terrainHeight;
    }

    public int getWidthInTiles() {
        return Params.terrainWidth;
    }

    public void pathFinderVisited(int x, int y) {
        visited[x][y] = true;
    }
    
    public int getUnit(int x, int y) {
        return units[x][y];
    }

    public void setUnit(int x, int y, int unit) {
        units[x][y] = unit;
    }
    
}
