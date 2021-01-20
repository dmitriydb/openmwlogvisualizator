package openmwlogvisualizator;

/**
 * Creates MapStateChange commands and executes them if new log line contains useful data
 * @author Dmitriy D
 */
public class LogMatcher {
    public static void parseLogLine(String line){
        if (line.contains("Loading cell")){
            new LoadCellStateChange(Map.getMap()).stateChanged(line);
        }
        if (line.contains("Unloading cell")){
            new UnloadCellStateChange(Map.getMap()).stateChanged(line);
        }
       
    }
    
}
