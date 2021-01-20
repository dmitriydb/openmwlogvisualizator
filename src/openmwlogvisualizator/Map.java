package openmwlogvisualizator;

import java.util.*;
/**
 *
 * @author Dmitriy D
 */
public class Map {
    
    private static Map mapInstance;
    private volatile String currentLocation = ""; 
    
    public static synchronized Map getMap(){
        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
            
    }
    
    public static int ROWS = 42;
    public static int COLUMNS = 36;
    public static int LOADED = +1;
    public static int UNLOADED = -1;
    
    private Cell[][] cells = new Cell[Map.ROWS][Map.COLUMNS];
    
    private ArrayList<MapStateChangeListener> listenerList = new ArrayList<MapStateChangeListener>();
    
    private Map (){
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                cells[i][j] = new Cell();
            }
        }
        
    }
    
    public synchronized boolean getCell(int i, int j){
        return cells[i][j].isLoaded();
    }
    
    /**
     * Converts Morrowind log relative coordinates to array coordinates
     * see map picture for additional info
     * @param x
     * @param y
     * @return a pair of two new coordinates
     */
    public static int[] conversRelativeCoords(int x, int y){
     
        int[] newCoords = new int[2];
        newCoords[0] = -(y-25);
        newCoords[1] = x+15;
        return newCoords;
    }
    
    public synchronized void setLocation(String newLocation){
        this.currentLocation = newLocation;
        System.out.println(newLocation);
    }
    
    public synchronized String getLocation(){
        return currentLocation;
    }
    
    public void addListener(MapStateChangeListener x){
        listenerList.add(x);
    }
    
    public void notifyListeners(){
    
        for (MapStateChangeListener x : listenerList){
            x.update();
        }
    }
    
    public void loadCell(int x, int y){
        cells[x][y].load();
    }
    
    public void unloadCell(int x, int y){
        cells[x][y].unload();
    }
}
