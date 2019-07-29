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
class ConnectorCore {

    private String userName, password, ip;
    private int port;
    private boolean loggedOn = false;
    private NetworkThread thread = null;

    public ConnectorCore(String username, String passWord, String IP, int por) {
        userName = username;
        try {
            password = new BigInteger(CryptTools.getSHA256(passWord)).toString();
        } catch (Exception e) {
            password = new BigInteger(passWord).toString();
        }
        ip = IP;
        port = por;
    }

    public ConnectorCore(String username, BigInteger passWord, String IP, int por) {
        userName = username;
        try {
            password = passWord.toString();
        } catch (Exception e) {

        }
        ip = IP;
        port = por;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean startNetworkThread() throws IllegalArgumentException {
        return startThread();
    }

    public String sendRequest(String query) throws IllegalArgumentException {
        return sendQuery(query);
    }
    
    public String sendConsoleRequest(String query) throws IllegalArgumentException {
        return sendStat(query);
    }

    private boolean startThread() throws IllegalArgumentException {
        thread = new NetworkThread(this);
        thread.start();
        String t = thread.getResponse();
        if (t != null && "Invalid Loggin".equals(t)) {
            throw new IllegalArgumentException("Invalid Loggin");
        } else {
            loggedOn = true;
            return true;
        }
    }

    private String sendQuery(String query) throws IllegalArgumentException {
        thread = new NetworkThread(this);     
        thread.setRequest("-q " + query);
        thread.start();
        return thread.getResponse();
    }
    
    private String sendStat(String query) throws IllegalArgumentException {
        thread = new NetworkThread(this);     
        thread.setRequest(query);
        thread.start();
        return thread.getResponse();
    }

}
