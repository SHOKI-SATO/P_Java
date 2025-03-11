package projava;
import java.io.IOException;

public class Maze {
    public static void main(String[] args) throws IOException {

        System.out.println("操作キー：A←,W↑,S→,Z↓");

        record Position(int x, int y){}
        int[][]map = {
                {1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}
        };
        var current = new Position(1, 1);
        var goal = new Position(4, 3);
        for(;;){
            //縦方向の処理を表す →"y"
            for(int y = 0; y < map.length; y++){
                //横方向の処理を表す　→"x"
                for (int x = 0; x < map[y].length; x++) {
                    if(x == current.x() && y == current.y()){
                        System.out.print("o");
                    }else if(map[y][x] == 1){
                        System.out.print("*");
                    }else{
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            if(current.equals(goal)){
                System.out.println("GOAL!!!");
                break;
            }
            int ch = System.in.read();
            var next = switch(ch){
                case 'a' -> new Position(current.x()-1, current.y());
                case 'w' -> new Position(current.x(), current.y()-1);
                case 's' -> new Position(current.x()+1, current.y());
                case 'z' -> new Position(current.x(), current.y()+1);
                default -> current;
            };

            if (map[next.y()][next.x()] == 0){
                current = next;
            }
            System.in.read();
        }
    }
}
