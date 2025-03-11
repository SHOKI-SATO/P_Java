package projava;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class SimpleClient2 {
    public static void main(String[] args) throws IOException {
        //try-with-resouceで必ず処理を閉じるようにする
        try(var soc = new Socket("localhost", 1600);
            OutputStream is = soc.getOutputStream())
        {
            is.write(234);
            //tryを抜けるので、tryで指定したSocketのclose()が呼び出される
        }catch(ConnectException c){
            System.out.println("サーバーが起動していません");
        }
    }

}
