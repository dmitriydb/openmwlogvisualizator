package openmwlogvisualizator;

/**
 *
 * @author Dmitriy D
 */
/**
 * Represents a map cell
 * @author Dmitriy D
 */
public class Cell {
    
    /**
     * true, if the cell is loaded
     */
    private boolean isLoaded = false;
    /**
     * true, if the cell was visited at least once
     */
    private boolean isVisited = false;
    
    /**
     * visit and load the cell
     */
    public void load(){
        isVisited = isLoaded = true;
    }
    
    /**
     * sets isLoaded as true
     */
    public void unload(){
        isLoaded = false;
    }
    
    public boolean isLoaded(){
        return isLoaded;
    }
    
    public boolean isVisited(){
        return isVisited;
    }
    
}
