package projava;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer2 {
    public static void main(String[] args) throws IOException {
        var server = new ServerSocket(1600);
        System.out.println("Waiting...");

        try(Socket soc = server.accept();
            InputStream input = soc.getInputStream())
        {
            System.out.println("connct from " + soc.getInetAddress());
            System.out.println(input.read());
        }
    }
}
