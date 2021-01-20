package openmwlogvisualizator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
/**
 * Creates MapStateChange commands and executes them if new log line contains useful data
 * @author Dmitriy D
 */
public class LogMatcher {
    public static void parseLogLine(String line){
        if (line.contains("Loading cell")){
            
            Pattern pattern = Pattern.compile("(-)*\\d, (-)*\\d");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find())
            new LoadCellStateChange(Map.getMap()).stateChanged(line);
            else
               new LocationStateChange((Map.getMap())).stateChanged(line);
        }
        if (line.contains("Unloading cell")){
            new UnloadCellStateChange(Map.getMap()).stateChanged(line);
        }
       
    }
    
}
