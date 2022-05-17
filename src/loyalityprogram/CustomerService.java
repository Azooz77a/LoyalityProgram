
package loyalityprogram;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomerService {

    private Socket socket =null;
    private ServerSocket server=null;
    private DataInputStream in=null;
    
    
    public CustomerService(int port){
    
        try{
            server =new ServerSocket(port);
            System.out.println("Customer service started");
            
            System.out.println("Waiting for The Customer ....");
            
            socket =server.accept();
            System.out.println("Customer accepted");
            
            // takes input from client socket
            in=new DataInputStream((new BufferedInputStream(socket.getInputStream())));
            
            String line="";
        
            //read mesage from client untill "over"
            while(!line.equals("over")){
                try{
                    line =in.readUTF();
                    
                    System.out.println(line);
                }catch(IOException e){
                    System.out.println(e);
                }
            }
            System.out.println("The complaints has been recived");
            
            // close connection
            socket.close();
            in.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
}
    public static void main(String[] args) {

        CustomerService server =new CustomerService(500);
    }
    
   
    
}
