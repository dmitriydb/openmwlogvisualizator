package openmwlogvisualizator;

/**
 *  
 * @author Dmitriy D
 */
public class Main {

    public static void main(String[] args) {
        
       Map.getMap().addListener(GUI.getGUI());
       GUI.getGUI().start();
    }
    
}
