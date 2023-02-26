package projava;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

//SSL/TSLを使い暗号化した通信
public class WebClient1 {
    public static void main(String[] args) throws IOException {
        var domain = "www.google.com";
        //SSLSocketFactoryメソッドでSocketFactoryオブジェクトを得る
        SocketFactory factory = SSLSocketFactory.getDefault();
        //SocketFactoryオブジェクトに対してアドレスとポート番号を指定し、createSocketメソッドを呼び出す
        //　→SSL通信を行うSocketオブジェクトを得られる
        try (Socket soc = factory.createSocket(domain, 443);
             var pw = new PrintWriter(soc.getOutputStream());
             var isr = new InputStreamReader(soc.getInputStream());
             var bur = new BufferedReader(isr)) {


            pw.println("GET /index.html HTTP/1.1");
            pw.println("Host: " + domain);
            pw.println();
            pw.flush();
            bur.lines()
                    .limit(18)
                    .forEach(System.out::println);
        }
    }
}

