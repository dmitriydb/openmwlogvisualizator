
package openmwlogvisualizator;

/**
 *
 * @author Dmitriy D
 */
public class LocationStateChange extends MapStateChange{
    
    private Map map;
    
    LocationStateChange(Map map){
        super(map);
    }
    
    public void stateChanged(String s){
        System.out.println(s);
        String loc = s.substring(13);
        Map.getMap().setLocation(loc);
        try{
           
        Map.getMap().notifyListeners();
        Thread.sleep(2000);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
}
