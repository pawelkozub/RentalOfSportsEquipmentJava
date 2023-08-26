package Main;

//Client Server Communication via Sockets
//Author- Aaditya Bagga		29-09-2013
//Server

import java.io.*;
import java.net.*;

class MyServer implements Runnable{
	//Declare class variables to be used
    String sent="",recieved=null;
    ObjectInputStream ois=null;
    ObjectOutputStream oos=null;
    BufferedReader br=null;
    ServerSocket s=null;
    
    public void run(){
        try{
            //1. Create a server socket
            s=new ServerSocket(3333);
            System.out.println("Client-Server Chat \n-----------------------------------------------------------\nPress Alt+F4 to quit this application.");
            
            //2. Wait for connection
            System.out.println("\nWaiting for connection from Client");
            Socket connection=s.accept();
            System.out.println("Connection received from "+connection.getInetAddress().getHostName()+":"+connection.getInetAddress());
            System.out.println("Waiting for response.. ");


            //3. Get Input and Output streams
            oos=new ObjectOutputStream(connection.getOutputStream());
            oos.flush();
            ois=new ObjectInputStream(connection.getInputStream());

            br=new BufferedReader(new InputStreamReader(System.in));
            
            while(true){
                    receiveMessage();

                    System.out.println("Enter message: ");					
                    sendMessage();

                    System.out.println("Waiting for response.. ");
            }
        }catch(Exception e){
            System.out.println(e);
        }	

    }
    
    void receiveMessage(){
        try{
            recieved=(String)ois.readObject();
            System.out.println("\nClient->");
            System.out.println(recieved);
        }catch(Exception e){
            System.out.println(e);
        }
		
    }

    void sendMessage(){
        try{
            System.out.println("(Type y and press enter to terminate the message)");
            String temp=null;
            sent="";

            while(true){
                    temp=br.readLine();
                    if(temp.equalsIgnoreCase("y"))
                            break;
                    else
                            sent=sent+temp+"\n";

            } 
            oos.writeObject(sent);
            oos.flush();
        }catch(Exception e){
            System.out.println(e);
        }
    }
		
    public static void main(String args[]){
        MyServer s=new MyServer();
        s.run();
    }
}  	    
	
			
