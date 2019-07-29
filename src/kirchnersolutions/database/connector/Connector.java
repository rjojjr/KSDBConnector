/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kirchnersolutions.database.connector;

import java.math.BigInteger;

/**
 *
 * @author rjojj
 */
public class Connector {
    
    public static final String VERSION = "1.0.03b";
    
    private ConnectorCore core;
    private boolean loggedOn = false;
    
    public Connector(String userName, String password, String ip, int port){
        core = new ConnectorCore(userName, password, ip, port);
    }
    
    public Connector(String userName, BigInteger password, String ip, int port){
        core = new ConnectorCore(userName, password, ip, port);
    }
    
    public void start() throws IllegalArgumentException{
        loggedOn = core.startNetworkThread();
    }
    
    public String queryDB(String query) throws IllegalArgumentException{
        return core.sendRequest(query);
    }
    
    public String consoleDB(String query) throws IllegalArgumentException{
        return core.sendConsoleRequest(query);
    }
}
