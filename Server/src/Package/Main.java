
package Package;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main 
{
    public static void main(String...Bongo)
    {
        Frame object = new Frame();
        
        
        object.frame();
        object.server_opening();
        
        object.Message_Receiving_Object_Creation();
        
        
    }
    
}

class Frame implements ActionListener
{
    JFrame f;
    JLabel l1;
    JTextArea ta1,ta2,ta3;
    JScrollPane sp1,sp3;
    JButton b1;
    
    int port = 33344;
    ServerSocket ssoc;
    Socket soc;
    DataOutputStream dos;
    DataInputStream dis;
    String sending_string="",receiving_string="",displaying_string="";
    
    void frame()
    {
       f = new JFrame("Chatting");
       f.setBounds(0,0,370,700);
       f.setBackground(Color.green);
       ta1 = new JTextArea();
       ta1.setBackground(Color.yellow);
       sp1 = new JScrollPane(ta1);
       sp1.setBounds(20,30,300,400);
       f.add(sp1);
       l1 = new JLabel("Enter Your Message:");
       l1.setBounds(20,560,350,30);
       f.add(l1);
       ta3 = new JTextArea();
       sp3 = new JScrollPane(ta3);
       sp3.setBounds(20,583,230,40);
       f.add(sp3);
       b1 = new JButton("SEND");
       b1.setBounds(255,583,80,38);
       f.add(b1);
       b1.addActionListener(this);
       
       
       f.setLayout(null);
       f.setVisible(true);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    class Message_Sending extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                sending_string=ta3.getText();
                displaying_string += "Me-> " + sending_string + "\n\n"; 
                ta1.setText(displaying_string);
                ta3.setText("");
                dos.writeUTF(sending_string);
                
            }
            catch(Throwable t)
            {
                
            }
                
        }
    }
    class Message_Receiving extends Thread
    {
        @Override
        public void run()
        {
            try 
            {
                receiving_string = dis.readUTF();
                while(!receiving_string.equalsIgnoreCase("exit"))
                {
                    
                    displaying_string += "Client-> " + receiving_string + "\n\n"; 
                    ta1.setText(displaying_string);
                    receiving_string = dis.readUTF();
                }
                
            }
            catch (Throwable t) 
            {
            
            }
        }
    }
    
    void Message_Receiving_Object_Creation()
    {
        Message_Receiving obj = new Message_Receiving();
        obj.start();
    }
    
    
    
    
    public void actionPerformed(ActionEvent event)
    {
        try 
        {
            Message_Sending obj = new Message_Sending();
            obj.start();
        }
        catch (Throwable t) 
        {
            
        }
    }
    void server_opening()
    {
        try
        {
           ssoc = new ServerSocket(port);
           soc = ssoc.accept();
           dos = new DataOutputStream(soc.getOutputStream());
           dis = new DataInputStream(soc.getInputStream());
        }
        catch(Throwable t)
        {
            System.out.print(t);
        }
    }
    
    
}




