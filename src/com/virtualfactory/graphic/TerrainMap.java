package com.virtualfactory.graphic;

import com.virtualfactory.pathfinding.TileMap;
import com.virtualfactory.utils.Params;

/**
 *
 * @author David Bengoa
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
    
    @Override
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
    
    @Override
    public float getCost(int sx, int sy, int tx, int ty) {
        return costs[sx][sy];
    }

    @Override
    public int getHeightInTiles() {
        return Params.terrainHeight;
    }

    @Override
    public int getWidthInTiles() {
        return Params.terrainWidth;
    }

    @Override
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