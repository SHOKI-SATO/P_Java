package projava;

//カッコが正しい数あるかどうかの判定
public class CheckParentheses {
    //判定する文字列を引数(data)で受け取る
    //カッコの数が正しければ（「(」と「)」の数が同じ）trueを返す
    static boolean check(String data) {
        //変数countでカッコの数を管理
        int count = 0;
        for (var ch : data.toCharArray()) {
            switch (ch) {
                //「(」の場合、countを増やす
                case '(' -> count++;
                //「)」の場合、countをへらす
                case ')' -> {
                    count--;
                    //countがマイナスになったらその時点でfalseを返す
                    if (count < 0) {
                        return false;
                    }
                }
            }
        }
        //最終的にcountが0になればカッコの数は正しいのでtrueを返す
        //マイナスの場合は18行目でfalseを返しており、プラスの場合はここでfalseとなる
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println(check("((test(data))test)" ));
        System.out.println(check("((test(data)test)" ));
        System.out.println(check("()test)data(test" ));
    }

}