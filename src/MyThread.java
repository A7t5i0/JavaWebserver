package src;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread implements Runnable{
    public Socket sock;
    public MyThread(Socket sock1) {
        sock = sock1;
    }


    @Override
    public void run() {

        InputStream sis;
        try {
            sis = sock.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(sis));
        String request;
        try {
            request = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(request);
        if (request.contains("GET")) {
            String[] requestParam = request.split(" ");
            String method = requestParam[0];
            String path = requestParam[1];
            System.out.println(method);
            System.out.println(path);
                    
            String filename = path.substring(1);
            File filenamef = new File(filename);
            String filename2 = filename + ".html";
            File filename2f = new File(filename2);
            System.out.println(filename);
            System.out.println(filename2);

            PrintWriter out;
            try {
                out = new PrintWriter(sock.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String contentType;
            if (path.contains(".js")) {
                contentType = "Content-Type: application/javascript; charset=UTF-8\r\n";
            } else if (path.contains(".mp4")) {
                contentType = "Content-Type: video/mp4; charset=UTF-8\r\n";
            } else if (path.contains(".png")) {
                contentType = "Content-Type: media/png; charset=UTF-8\r\n";
            } else {
                contentType = "Content-Type: text/html; charset=UTF-8\r\n";
            }
            if (path.equals("/")) {
                File file = new File("index.html");
                long filesize = file.length();
                System.out.println("filesize: "+filesize);
                String headers = "HTTP/1.1 200 OK\r\nDate: " + date + " GMT\r\nServer: GNU/linux\r\nLast-Modified: Tue, 8 Mar 2022 15:53:59 GMT\r\n" + contentType + "Content-Length: " + filesize + "\r\nConnection: Close\r\n\r\n";
                out.write(headers);
                out.flush();
                InputStream inputstream;
                try {
                    inputstream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                DataOutputStream outputstream;
                try {
                    outputstream = new DataOutputStream(sock.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int count;
                byte[] buffer = new byte[8192];
                while(true) {
                    try {
                        if (!((count = inputstream.read(buffer)) > 0)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        outputstream.write(buffer, 0, count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                    
            } else if(filenamef.exists()) {
                File file = new File(filename);
                long filesize = file.length();
                String headers = "HTTP/1.1 200 OK\r\nDate: " + date + " GMT\r\nServer: GNU/linux\r\nLast-Modified: Tue, 8 Mar 2022 15:53:59 GMT\r\n" + contentType + "Content-Length: " + filesize + "\r\nConnection: Close\r\n\r\n";
                out.write(headers);
                out.flush();
                InputStream inputstream;
                try {
                    inputstream = new FileInputStream(filenamef);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                DataOutputStream outputstream;
                try {
                    outputstream = new DataOutputStream(sock.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int count;
                byte[] buffer = new byte[8192];
                while(true) {
                    try {
                        if (!((count = inputstream.read(buffer)) > 0)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        outputstream.write(buffer, 0, count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if(filename2f.exists()) {
                System.out.println("entered filename2");
                File file = new File(filename2);
                long filesize = file.length();
                String headers = "HTTP/1.1 200 OK\r\nDate: " + date + " GMT\r\nServer: GNU/linux\r\nLast-Modified: Tue, 8 Mar 2022 15:53:59 GMT\r\n" + contentType + "Content-Length: " + filesize + "\r\nConnection: Close\r\n\r\n";
                out.write(headers);
                out.flush();
                InputStream inputstream;
                try {
                    inputstream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                DataOutputStream outputstream;
                try {
                    outputstream = new DataOutputStream(sock.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int count;
                byte[] buffer = new byte[8192];
                while(true) {
                    try {
                        if (!((count = inputstream.read(buffer)) > 0)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        outputstream.write(buffer, 0, count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.out.println("entered else 404");
                File file = new File("404.html");
                contentType = "Content-Type: text/html; charset=UTF-8\r\n";
                long filesize = file.length();
                System.out.println("filesize: "+filesize);
                String headers = "HTTP/1.1 200 OK\r\nDate: " + date + " GMT\r\nServer: GNU/linux\r\nLast-Modified: Tue, 8 Mar 2022 15:53:59 GMT\r\n" + contentType + "Content-Length: " + filesize + "\r\nConnection: Close\r\n\r\n";
                out.write(headers);
                out.flush();
                InputStream inputstream;
                try {
                    inputstream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                DataOutputStream outputstream;
                try {
                    outputstream = new DataOutputStream(sock.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int count;
                byte[] buffer = new byte[8192];
                while(true) {
                    try {
                        if (!((count = inputstream.read(buffer)) > 0)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        outputstream.write(buffer, 0, count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.close();

        } else {
            System.out.println("request type not yet implemented");
        }
    }
}