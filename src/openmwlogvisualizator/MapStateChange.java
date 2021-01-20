/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package openmwlogvisualizator;

/**
 *
 * @author Dmitriy D
 */
public abstract class MapStateChange implements MapStateChangeCommand{
    
    private Map map;
    
    public MapStateChange(Map map){
        this.map = map;
    }
    
    public void stateChanged(String state){
        map.notifyListeners();
    }
    
}
