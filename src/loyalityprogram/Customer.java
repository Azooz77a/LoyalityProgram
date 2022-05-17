/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loyalityprogram;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author PC
 */
public class Customer {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Customer(String address, int port) {

        //establish a conn
        try {
            socket = new Socket(address, port);
            System.out.println("Customer: Connected");

            //takes input from terminal
            System.out.println("What is your problem: *write \"over\" when you finish\nCustomer: ");
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownError e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // string to reaad message from input
        String line = "";

        //keep reading untill "over"
        while (!line.equals("over")) {

            try {
                line = input.readLine();
                out.writeUTF(line);
            } catch (IOException io) {
                System.out.println(io);
            }
        }
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException o) {
            System.out.println(o);
        }

    }

    public static void main(String[] args) {

        Customer client = new Customer("127.0.0.1", 500);
    }
}