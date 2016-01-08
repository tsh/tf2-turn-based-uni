package io.gameclient.game;


public class World {
    int[][] map;


    public World(){
        map = new int[][]{
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 55, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    public int[][] getMap(){
        return this.map;
    }
}
