package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServer {
    
    public static void main(String[] args) {
        ServerSocket serverSock;
        try {
            serverSock = new ServerSocket(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            Socket sock;
            try {
                sock = serverSock.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MyThread mythread = new MyThread(sock);
            Thread thread = new Thread(mythread);
            thread.start();
        }
    }
}