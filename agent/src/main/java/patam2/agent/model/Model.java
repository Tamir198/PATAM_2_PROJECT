package patam2.agent.model;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;

public class Model extends Observable {

    HashMap<String, String> SymbolTable;
    Socket fg;
    Socket fgServer;
    TimeSeries timeSeries;
    String fg_root;
    PrintWriter out2fg;

    public Model(String propertiesFileName){
        SymbolTable = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(propertiesFileName))){
            String line;
            while ((line=in.readLine()) != null){

                // splits property value on the left and value on the right from properties.txt
                String splitProperties[] = line.split(",");

                // push into the map
                SymbolTable.put(splitProperties[0],splitProperties[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // connecting to the flight gear as a client socket
        int port = Integer.parseInt(SymbolTable.get("port"));
        try {
            fg = new Socket(SymbolTable.get("ip"),port);
            out2fg = new PrintWriter(fg.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // connecting to the flight gear as a server

    }

    public void setAileron(double x){
        // we'll get the command: set /controls/flight/aileron, and write it to the socket's outPutStream with the given x
        String command = SymbolTable.get("aileron");
        out2fg.println(command+" "+x);
        out2fg.flush();
    }

    public void setElevators(double x){
        String command = SymbolTable.get("elevators");
        out2fg.println(command+" "+x);
        out2fg.flush();
    }

    public void setRudder(double x){
        String command = SymbolTable.get("rudder");
        out2fg.println(command+" "+x);
        out2fg.flush();
    }

    public void setThrottle(double x){
        String command = SymbolTable.get("throttle");
        out2fg.println(command+" "+x);
        out2fg.flush();
    }

    public void setBrakes(double x){
        String command = SymbolTable.get("brakes");
        out2fg.println(command+" "+x);
        out2fg.flush();
    }

    public void setFg_root(String path){
        String command = SymbolTable.get("root");
        out2fg.println(command+" "+path);
        out2fg.flush();
    }

    public void setTimeSeries(String csvFile){
        timeSeries = new TimeSeries(csvFile);
    }

    @Override
    public void finalize(){
        // close the socket
        try {
            out2fg.close();
            fg.close();
            fgServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
