package projava;

public class IfSample {
    public static void main(String[] args) {
        var a = 9;
        if (a < 3) {
            System.out.println("小さい");
        } else if (a < 7){
            System.out.println("中くらい");
        } else {
            System.out.println("大きい");
        }
    }
}

