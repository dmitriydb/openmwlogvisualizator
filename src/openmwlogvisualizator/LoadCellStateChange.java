package openmwlogvisualizator;

/**
 *
 * @author Dmitriy D
 */
/**
 * MapStateChange Command implementation
 * Loads a cell in the map
 * @author Dmitriy D
 */
public class LoadCellStateChange extends MapStateChange{
    
    public LoadCellStateChange(Map map){
        super(map);
    }
    
    public void stateChanged(String state){
        
        state = state.replaceAll("[^-?0-9]+", " "); 
        String[] coords = state.trim().split(" ");
        try{
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
      
        int[] convertedCoords = Map.conversRelativeCoords(x, y);
      
        Map.getMap().loadCell(convertedCoords[0], convertedCoords[1]);
        Map.getMap().notifyListeners();
        GUI.getGUI().repaint();
        
        Thread.sleep(100);
        }
        catch (Exception ex){
            //
        }
    }
    
}
