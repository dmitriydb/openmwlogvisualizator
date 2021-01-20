package openmwlogvisualizator;

/**
 * Simple MapListener console implementation
 * Does nothing right now
 * @author Dmitriy D
 */
public class ConsoleWriter implements MapStateChangeListener {
    
    private Map map;
    
    ConsoleWriter(Map map){
       this.map = map;    
    }
    
    public void update(){  
    }
    
}
