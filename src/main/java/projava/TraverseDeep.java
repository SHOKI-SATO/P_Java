package projava;

//迷路（再帰を使った深さ優先探索、スタック）
public class TraverseDeep {
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
            switch (map[curY][curX]) {
                case 0:
                    break; //通路なので続きの処理
                case 2:
                    return true; //ゴールなので、到達を表すtrueを返す
                default:
                    return false; //それ以外は壁か一度通ったところ（＝通れないを表すfalseを返す）
            }
            map[curY][curX] = 3; //処理が進んだ場合、後戻り防止のために通った印を残す
            //上下左右に移動して同じ処理を行うようtraverseメソッドを再帰呼び出しする
            if (traverse(map, curX + 1, curY) ||
                    traverse(map, curX - 1, curY) ||
                    traverse(map, curX, curY + 1) ||
                    traverse(map, curX, curY - 1)) {
                return true;
            }
            map[curY][curX] = 0; //ゴールに辿り着かなかったので通った印を戻す
            return false;
        }
    }
