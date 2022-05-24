package src;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
//    final static String pathToStores = "/tmp/ssl/server"; //dir name
    final static String keyStoreFile = "keystore.jks"; // filename
    final static String passwd = "1234qwer"; //password
    static boolean debug = false;
    final static int theServerPort = 5000;
    public static void main(String[] args) throws IOException {
        String trustFilename = keyStoreFile;

        System.setProperty("javax.net.ssl.keyStore", trustFilename);
        System.setProperty("javax.net.ssl.keyStorePassword", passwd);
        if(debug){
            System.setProperty("javax.net.debug", "all");
        }

        SSLServerSocketFactory sslssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket = (SSLServerSocket) sslssf.createServerSocket(theServerPort);

//        ServerSocket ss;
//        ss = new ServerSocket(5000);
        while(true){
            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
//            Socket s1;
//            s1 = ss.accept();
//            MyThread mt = new MyThread(s1);
            MyThread mt = new MyThread(sslSocket);
            Thread t = new Thread(mt);
            t.start();
        }
    }
}

//    SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("www.google.com", 443);
//s.startHandshake();
//        s.setSoTimeout(60 * 1000);


////SSL
//final static String pathToStores = "/tmp/ssl/server"; //dir name
//final static String keyStoreFile = "server.keystore"; // filename
//final static String passwd = "1234qwer"; //password
//
//final static int theServerPort = 1234;
//
//static boolean debug = false;
//
//void doServerSide() throws Exception{
//    SSLServerSocketFactory sslssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//    SSLServerSocket sslServerSocket = (SSLServerSocket) sslssf.createServerSocket(theServerPort);
//
//    SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
//
//}

////ssl in main
//String trustFilename = pathToStores + "/" + keyStoreFile;
//
//Sstem.setProperty("javax.net.ssl.keyStore", trustFilename);
//System.setProperty("javax.net.ssl.keyStorePassword", passwd);
//if(debug){
//    System.setProperty("javax.net.debug", "all");
//}
//new SSLServerExample().doServerSide(); //start server