package projava;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        //TCP通信ではSocket、ServerSocketクラスを使う（UDP通信ではDatagramSocketクラスを使う）
        // コンストラクタにはポート番号を指定
        var server = new ServerSocket(1700);
        System.out.println("Waiting...");
        //クライアントからの接続要求を待機
        Socket soc = server.accept();
        System.out.println("connect from " + soc.getInetAddress());
        //データを入出力する際にInputStream（OutputStream）クラスを使う
        InputStream input = soc.getInputStream();
        //データを受信
        System.out.println(input.read());
        input.close();
        soc.close();
    }
}
//上記のソースでは、例外が発生した際にcloseが呼び出されずに終わる可能性がある
//→ closeが必要な場合にか非close出来る構文として、「try-with-resouce」がある
