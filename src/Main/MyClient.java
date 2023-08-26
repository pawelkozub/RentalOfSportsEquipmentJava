package Main;

//Client Server Communication via Sockets
//Author- Aaditya Bagga		29-09-2013
//Client

import java.io.*;
import java.net.*;

class MyClient {
	//Declare class variables to be used 
	
    String sent="",recieved=null;
    ObjectInputStream ois=null;
    ObjectOutputStream oos=null;
    BufferedReader br=null;
    Socket s=null;
    
    public void runCli(){
        try{
            s=new Socket("localhost",3333);

            System.out.println("Client-Server Chat \n-----------------------------------------------------------\nPress Alt+F4 to quit this application.");

            System.out.println("\nSuccessfully connected to Server-"+s.getInetAddress()+":"+s.getLocalPort()+"\n");
            ois=new ObjectInputStream(s.getInputStream());
            oos=new ObjectOutputStream(s.getOutputStream());
            br=new BufferedReader(new InputStreamReader(System.in));
            
            while(true){
                System.out.println("Enter message: ");
                sendMessage();

                System.out.println("Waiting for response.. ");
                receiveMessage();
            }
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
	
    void receiveMessage(){
        try{
            recieved=(String)ois.readObject();
            System.out.println("\nServer->");
            System.out.println(recieved);
        }catch(Exception e){
            System.out.println(e);	
        }	
    }
    
    public static void main(String args[]){
        MyClient c1=new MyClient();
	c1.runCli();
    }
}
