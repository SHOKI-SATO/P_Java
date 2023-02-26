package projava;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//Webブラウザから接続を受け付けるサーバー
//処理としてはWebクライアントの逆になる
public class WebServer {
    public static void main(String[] args) throws IOException {
        //まず、8880番ポートで待ち受けるソケットを用意
        var server = new ServerSocket(8880);
        //何度もリクエストを受け付けるために、処理全体を無限ループで囲む（for文に何も指定しない）
        for(;;){
            //acceptメソッドで通信を待ち受ける
            //クライアントから接続を受け取ったら、その接続を管理するSocketからBufferedReaderとPrintWriterを得る
            //これらが確実にcloseされるよう、try-with-resources構文を使用
            try(Socket soc = server.accept();
            var isr = new InputStreamReader(soc.getInputStream());
            var bur = new BufferedReader(isr);
            var w = new PrintWriter(soc.getOutputStream()))
            {
                System.out.println("connected from " + soc.getInetAddress());

                //接続を受け取ったらリクエストヘッダーを読み込む
                //条件が成り立つ間Streamを処理するtakeWhileメソッドを使用
                // リクエストヘッダー終了の合図である空行が来るまで出力処理を行う
                bur.lines()
                        .takeWhile(line -> !line.isEmpty())
                        .forEach(System.out::println);

                //HTTPではレスポンスヘッダー、データ本体の間に改行コードが2つ必要
                //レスポンスとしてはステータスコードとして200を送って、Content-Typeヘッダーにtext/htmlを指定
                //レスポンスのhtmlとして＜html＞以降を返却
                //ちなみに、、Content-Type：text/planeを指定すると、htmlとしてではなく単なる文字列として表示される
                w.println("""
            HTTP/1.1 200 OK
            Content-Type: text/html
            
            <html><head><title>Hello</title></head>
            <body><h1>Hello</h1>It works!</body></html>
            """);

                //http://localHost:8880にアクセスすると、表示されている
                //　→実行結果には、ブラウザから送られてきたリクエストヘッダーが表示される
                //　※一度実行すると終了しないので、動作確認が終わったら停止ボタンにより停止する
            }
        }
    }
}
