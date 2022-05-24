package src;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread implements Runnable{
//    public Socket s;
    public SSLSocket s;
    public MyThread(Socket sslSocket){s= (SSLSocket) sslSocket;}
    @Override
    public void run(){
        InputStream is;
        try {
            is = s.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        byte[] buf1 = new byte[1];
        String str;
        while(i!=-1){
            try {
//                System.out.println("i: " + i);
                i = is.read(buf1);
//                System.out.println(i);
//                System.out.println(buf1);
//                c = (char)i;
                str = new String(buf1, StandardCharsets.UTF_8);
                System.out.println(str);
                sb.append(str);
                System.out.println(sb);
                if (sb.toString().contains("\r\n\r\n")) {
                    System.out.println("breaking");
                    break;
                }
//                    buf2.write(buf1);

//                if(sb.toString().endsWith("\r\n\r\n")){
//                    break;
//                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("done");
        String req = sb.toString();
//        System.out.println(req);
        if(req.contains("GET")){
            System.out.println("GET method");
            String[] reqParams = req.split("\n");
            String[] methodParams = reqParams[0].split(" ");
            System.out.println(methodParams[1]);
            String url = methodParams[1];
            PrintWriter out;
            try {
                out = new PrintWriter(s.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String contentType;
            if (url.contains(".js")) {
                contentType = "Content-Type: application/javascript; charset=UTF-8\r\n";
            } else if (url.contains(".mp4")) {
                contentType = "Content-Type: video/mp4; charset=UTF-8\r\n";
            } else if (url.contains(".png")) {
                contentType = "Content-Type: image/png; charset=UTF-8\r\n";
            } else if (url.contains(".jpg")) {
                contentType = "Content-Type: image/jpg; charset=UTF-8\r\n";
            } else {
                contentType = "Content-Type: text/html; charset=UTF-8\r\n";
            }
            if(methodParams[1].equals("/")){
                System.out.println("serve index.html");
                File file = new File("index.html");
                long filesize = file.length();
                System.out.println("filesize: " + filesize);
                String headers = "HTTP/1.1 200 OK\r\nDate: " + date + " GMT\r\nServer: GNU/linux\r\nLast-Modified: Tue, 8 Mar 2022 15:53:59 GMT\r\n" + contentType + "Content-Length: " + filesize + "\r\nConnection: Close\r\n\r\n";
                out.write(headers);
                out.flush();
                InputStream inputstream;
                try {
                    inputstream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                OutputStream outputstream;
                try {
                    outputstream = s.getOutputStream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int count;
                byte[] buffer = new byte[8192];
                while (true) {
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
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(!methodParams[1].equals("/")) {
                String[] urlParams = url.split("/");
                String filename = urlParams[1];
                File filenamef = new File(filename);
                String filename2 = filename + ".html";
                File filename2f = new File(filename2);
                System.out.println(filename);
                System.out.println(filename2);
                if (filenamef.exists()) {
                    System.out.println("entered filename");
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
                    OutputStream outputstream;
                    try {
                        outputstream = s.getOutputStream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int count;
                    byte[] buffer = new byte[512];
                    while (true) {
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
                } else if (filename2f.exists()) {
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
                    OutputStream outputstream;
                    try {
                        outputstream = s.getOutputStream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int count;
                    byte[] buffer = new byte[512];
                    while (true) {
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
                    System.out.println("filesize: " + filesize);
                    String headers = "HTTP/1.1 200 OK\r\nDate: " + date + " GMT\r\nServer: GNU/linux\r\nLast-Modified: Tue, 8 Mar 2022 15:53:59 GMT\r\n" + contentType + "Content-Length: " + filesize + "\r\nConnection: Close\r\n\r\n";
                    out.write(headers);
                    out.flush();
                    InputStream inputstream;
                    try {
                        inputstream = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    OutputStream outputstream;
                    try {
                        outputstream = s.getOutputStream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int count;
                    byte[] buffer = new byte[8192];
                    while (true) {
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
            }
        }else if(req.contains("POST")){
            System.out.println("POST method");
            StringBuilder sb2 = new StringBuilder();
            String str2;
            byte[] buf2 = new byte[1];
            while(i!=-1){
                try {
                    i = is.read(buf2);
                    str2 = new String(buf2, StandardCharsets.UTF_8);
                    sb2.append(str2);
                    if (sb2.toString().contains("\r\n\r\n")) {
//                        System.out.println("breaking");
                        break;
                    }
//                    buf2.write(buf1);

//                if(sb.toString().endsWith("\r\n\r\n")){
//                    break;
//                }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String sb2str = sb2.toString();
            System.out.println(sb2str);
            String[] sb2strParams = sb2str.split("\r\n");
            String[] sb2strParams2 = sb2strParams[1].split(" ");
            System.out.println(sb2strParams2[3]);
            String filename = sb2strParams2[3];
//            System.out.println(filename);
            filename = filename.substring(10, filename.length() - 1);
            System.out.println(filename);
            System.out.println("STRIIIIIIIIIIIIIIIIIING333");
            File file = new File(filename);
            OutputStream os;
            try {
                os = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            int x = 0;
            while(true) {
                try {
                    if (!(is.available() > 0)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    x = is.read(buf2);
                    if(x == -1){break;}
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    os.write(buf2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("UPLOAD DONE");
        }else {
            System.out.println("Method not yet implemented");
        }
    }
}
