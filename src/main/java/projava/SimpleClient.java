package projava;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.SocketHandler;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        //TCP通信ではSocket、ServerSocketクラスを使う（UDP通信ではDatagramSocketクラスを使う）
        //Socketオブジェクト生成時のコンストラクタに「接続先ドメイン名（IPアドレス）とポート番号」を指定
        var soc = new Socket("localhost", 1700);
        //データを入出力する際にOutputStream（InputStream）クラスを使う
        OutputStream output = soc.getOutputStream();
        //データを送信（0~255の数字）、文字列データを送信する場合はWriterクラスを使う
        output.write(123);
        output.close();
        soc.close();
    }
}
//上記のソースでは、例外が発生した際にcloseが呼び出されずに終わる可能性がある
//→ closeが必要な場合にか非close出来る構文として、「try-with-resouce」がある
