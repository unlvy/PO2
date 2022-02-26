import java.net.*;
import java.io.*;


public class Client {

    private final static int PORT = 4321;

    public static void main(String[] args) throws Exception {
    
        String ip = args[0];
        Socket client = new Socket(ip, PORT);
        BufferedReader inr = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        // establish connection 
        System.out.println(inr.readLine());
        
        
        Runnable writer = () -> {
            try {
                String inl;
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                while ((inl = stdin.readLine()) != null) {
                    out.println(inl);
                    if (inl.equals("EXIT"))
                        System.exit(0);   
                }
            
            } catch (Exception ignored) {}
        };
        
        Runnable reader = () -> {
            try {
                
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String inl;
                while ((inl = in.readLine()) != null) {
                    System.out.println(inl);
                }
            
            } catch (Exception ignored) {}
        
        };
        
        Thread thread1 = new Thread(writer);
        Thread thread2 = new Thread(reader);
        thread1.start();
        thread2.start();
        
    
    }

}
