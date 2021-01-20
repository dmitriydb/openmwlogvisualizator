/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package openmwlogvisualizator;

import java.io.File;
import java.util.Arrays;
import java.io.*;

/**
 *
 * @author Dmitriy D
 */
public class LogReader {
    
    
    public void readLog(){
        String openmwlogPath = determineLogLocation();
        try{
        BufferedReader logReader = new BufferedReader(new FileReader(new File(openmwlogPath)));
        String s;
        while ((s = logReader.readLine()) != null)
            LogMatcher.parseLogLine(s);
        }
        catch (Exception ex){
            System.out.println("Openmw.log reading error!");
            System.out.println("Try to select it manually.");
            
            ex.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * @return openmw.log location (or user choice if there was any)
     * see https://wiki.openmw.org/index.php?title=Paths
     */
    private String determineLogLocation(){
        if (ResourceList.logLocation != "")
                return ResourceList.logLocation;
        if (System.getProperty("os.name").contains("win"))
            return "C:\\Users\\Username\\Documents\\my games\\openmw\\openmw.log";
        else
            return System.getProperty("user.home") + File.separator + ".config/openmw/openmw.log";
        
    }
}
