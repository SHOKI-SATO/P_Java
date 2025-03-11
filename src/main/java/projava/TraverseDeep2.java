package projava;

import java.util.ArrayDeque;

//迷路（再帰呼び出しをスタックを使った処理に置き換える、深さ優先探索、スタック）
public class TraverseDeep2 {
    public static void main(String[] args) {
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 1},
                {1, 0, 1, 0, 0, 2, 1},
                {1, 1, 1, 1, 1, 1, 1},
        };
        traverse(map, 1, 1);
        char[] ch = {'.', '*', 'G', 'o'};
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(ch[cell]);
            }
            System.out.println();
        }
    }
    static boolean traverse ( int[][] map, int curX, int curY){
        //引数で受け取っていた値をまとめてスタックに積む必要があるので、スタックに積むデータをまとめるためのレコードを用意
        // int型のxとyを保持するPositionレコードを用意（引数で受け取るmapは変更がないのでスタックに積む必要がない）
        record Position (int x, int y){}

        //スタックとしてArrayDequeオブジェクトを用意
        //（※Stackというクラスもあるが実装が古く、スタックを扱う場合はArrayDequeの方が適切）
        //Deque(デック)、先頭や末尾への追加削除の性能が良いデータ構造、両端キューともいう
        var stack = new ArrayDeque<Position>();
        //最初のtravarseメソッドを呼び出し時に渡していた引数をpushメソッドでスタックに積む
        stack.push(new Position(curX, curY));
        //スタックからpollFirstメソッドで値を取り出すとともに、nullではないときに処理を続けるよう判定
        for(Position p; (p = stack.pollFirst()) != null ;) {

            switch (map[p.y()][p.x()]) {
                case 0:
                    break; //通路なので続きの処理
                case 2:
                    return true; //ゴールなので、到達を表すtrueを返す
                default:
                    continue; //それ以外は壁か一度通ったところ（＝続きの処理を飛ばしてループを繰り返すcontinue文（他のマスの処理））
                                //break = switchを抜ける、　continue = for文の続きの処理を行う
            }
            map[p.y()][p.x()] = 3; //処理が進んだ場合、後戻り防止のために通った印を残す
            //再帰処理の代わりに、再帰のときにメソッドに与えるはずだった引数を、レコードに格納してスタックに積んでいる
            stack.push(new Position(p.x() + 1, p.y()));
            stack.push(new Position(p.x() - 1, p.y()));
            stack.push(new Position(p.x(), p.y() + 1));
            stack.push(new Position(p.x(), p.y() - 1));
        }
        return false;

        //後戻りの処理は難しくなるため、今回は入れていない
        //　→スタックは積み上げ（FILO、先入れ後出し）の為
        //　後戻りの処理に関しては、再帰の方が書きやすい
        }
}
