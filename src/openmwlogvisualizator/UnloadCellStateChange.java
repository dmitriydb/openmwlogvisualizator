package openmwlogvisualizator;

/**
 * MapStateChange Command implementation
 * Unloads a cell in the map
 * @author Dmitriy D
 */
public class UnloadCellStateChange extends MapStateChange{
    
    public UnloadCellStateChange(Map map){
        super(map);
    }
    
    @Override
    public void stateChanged(String state){
        state = state.replaceAll("[^-?0-9]+", " "); 
        String[] coords = state.trim().split(" ");
         try{
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
  
        int[] convertedCoords = Map.conversRelativeCoords(x, y);
        Map.getMap().unloadCell(convertedCoords[0], convertedCoords[1]);
        GUI.getGUI().repaint();
        
        Thread.sleep(100);
        }
        catch (Exception ex){
            //
        }
     
    }
}
