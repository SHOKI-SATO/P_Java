package projava;

import java.util.List;

public class InterfaceSample {
    record Student(String name, int score){
    }
    record Teature(String name, String subject){
    }

    public static void main(String[] args) {
        //StudentオブジェクトとTeatureオブジェクトが入ったリストを作成
        var people = List.of(new Student("kis", 80), new Teature("hosoya", "Math"));
        //peopleのそれぞれの値を取り出して処理を行う
        //StudentとTeatureの両方にnameメソッドがあるが、それだけでは統一的に扱えないので型としてnameメソッドを持つことを示す必要がある
        //instanceof・・・オブジェクトがある型として扱えるかを判定する演算子（続く型（この場合StudentとTeature）が扱える場合trueになる）
        //→Studentオブジェクトならtrueメソッドを呼び出し、Teatureオブジェクトならnameメソッドを呼び出し、それ以外なら「---」を返す
        for (var p : people){
            var n = p instanceof Student s ? s.name() :
                            p instanceof Teature s ? s.name() :
                                    "---";
            System.out.println("こんにちは%sさん".formatted(n));
        }
    }

}
