import java.net.*;
import java.io.*;
import java.util.ArrayList;

class Message {
    String mess;
    boolean isSet = false;

    synchronized String get() {
        if (!isSet) {
            try { wait(); } catch (Exception e) {}  
        }
        isSet = false; notify();
        return mess;
    }

    synchronized void set(String mess) {
        if (isSet) {
            try { wait(); } catch (Exception e) {}
        }
        this.mess = mess;
        isSet = true; notify();
    }

}

class ConnThread extends Thread {
            
            private final Socket client;
            private final int sleep = 100;
            private PrintWriter out;
            private BufferedReader in;
            private Message m;
            
            
            public ConnThread(Socket client, Message m) {
                this.m = m;
                this.client = client;
                try { 
                    out = new PrintWriter(client.getOutputStream(), true);
                    in = new BufferedReader( new InputStreamReader(client.getInputStream()));
                } catch (Exception igonred) {}
            }
            
            public PrintWriter getOutput() {
                return out;
            }
            
            @Override
            public void run() {
                try {     
                    
                    String clientAddress = "[" + client.getInetAddress() + ":" + client.getPort() + "]";
                    out.println("NEW USER " + clientAddress);
                    System.out.println("NEW USER " + clientAddress);
                    
                    String inl;
                    
                    while ((inl = in.readLine()) != null) {
                        m.set(clientAddress + " " + inl);
                        System.out.println(clientAddress + " " + inl);
                        if (inl.equals("EXIT")) 
                            return;
                    }
                    
                    } catch (Exception ignored) {}
            }
        
        } 

class WriterThread extends Thread {

    private ArrayList<PrintWriter> outputs;
    private Message m;
    
    WriterThread(ArrayList<PrintWriter> outputs, Message m) {
        this.outputs = outputs;
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = m.get();
                for (var out : outputs) {
                    if (out != null)
                        out.println(message);
                }
            } catch (Exception ignored) {
                //System.out.println("wtf " + igonred);
            }
        }
    }

}



public class Server {

    private final static int PORT = 4321;    

    public static void main(String[] args) throws Exception {
    
        Message m = new Message();
        ArrayList<PrintWriter> outputs = new ArrayList<>();

        ServerSocket server = new ServerSocket(PORT);
        WriterThread writerThread = new WriterThread(outputs, m);
        writerThread.start();

                
        while (true) {
            ConnThread connThread = new ConnThread(server.accept(), m);
            outputs.add(connThread.getOutput());
            connThread.start();
        }

    }

}

