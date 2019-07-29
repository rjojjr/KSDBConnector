/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kirchnersolutions.database.connector;

/**
 *
 * @author rjojj
 */
class NetworkThread extends Thread {

    private volatile boolean running, stop, loggedOn = false, ready = false, nextItr = false;
    private ConnectorCore connector;
    private volatile String request = null, response;

    ServerClient client;

    public NetworkThread(ConnectorCore con) {
        running = true;
        connector = con;
        stop = false;
        client = new ServerClient();
    }

    public String getResponse() {
        String t = "";
        while (running) {

        }
        t = response;
        return t;
    }

    public void setRequest(String req) {    
        request = req;
    }

    public void cleanResponse() {
        synchronized (this) {
            response = null;
        }
    }

    public void stopThread() {
        if (loggedOn) {
            setRequest("-stop");
        } else {
            stop = true;
        }
    }

    public void run() throws IllegalArgumentException {
        running = true;
        try {
            client.startConnection(connector.getIP(), connector.getPort());

            client.sendMessage(connector.getUsername() + "," + connector.getPassword());
            try{
                Thread.sleep(50);
            }catch (Exception e){
            }
            response = client.sendMessage(request);
            try{
                Thread.sleep(50);
            }catch (Exception e){
            }

            String t = client.sendMessage("-l");
            running = false;
            client.stopConnection();
            /**
            client.startConnection(connector.getIP(), connector.getPort());
            if (!loggedOn) {
                String temp = client.sendMessage(connector.getUsername() + "," + connector.getPassword());
                if ("<err>Not logged on<err>".equals(temp)) {
                    response = "Invalid Loggin";
                    ready = true;
                } else {
                    loggedOn = true;
                }
            }
            if (request != null && nextItr) {
                nextItr = false;
                if ("-stop".equals(request)) {
                    String t = client.sendMessage(request);
                    loggedOn = false;
                    stop = true;
                }
                if ("-q".equals(request.split(" ")[0])) {
                    String t = client.sendMessage(request);
                    if (t.contains("<err>")) {
                        t = " " + t;
                        t = t.split("<err>")[1];
                        ready = true;
                        throw new IllegalArgumentException("t");
                    } else {
                        response = t;
                        ready = true;
                    }
                }
            }
            client.sendMessage("-l");
            * **/
            running = false;
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            
        }

    }

}
