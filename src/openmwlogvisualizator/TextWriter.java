package openmwlogvisualizator;

/**
 * Another MapStateChangeListener implementation
 * Outputs loaded cell in [(x1, y1), (x2, y2) format]
 * @author Dmitriy D
 */
public class TextWriter implements MapStateChangeListener{
    Map map;
    
    
    public TextWriter(Map map){
        this.map = map;
    }
    
    public void update(){
        System.out.print("Loaded cells by now: ");
        for (int i = 0; i<Map.ROWS; i++){
            for (int j = 0; j<Map.COLUMNS; j++){
                if (map.getCell(i, j))
                   System.out.print("(" + i + ", " + j +") ");
            }
     
        }
        System.out.println();
        
    }
}
