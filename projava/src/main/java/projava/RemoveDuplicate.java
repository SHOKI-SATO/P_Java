package projava;

import javax.imageio.plugins.tiff.TIFFImageReadParam;

    //重複するデータを取り除く
public class RemoveDuplicate {
    public static void main(String[] args) {
        //処理するデータを文字列で持つ
        var data = "abcccbaabcc";
        //結果の文字列を構築するために、StringBuilderクラスを使用
        //（どんどん値を追加して文字列を構築してく際に使うクラス）
        var builder = new StringBuilder();
        //文字列の長さ分処理を繰り返す
        for (int i = 0; i < data.length(); i++) {
            //charAtメソッドで文字列から文字を取り出す(引数に指定した数値の一の文字を取り出す（先頭は０）)
            char ch = data.charAt(i);
            //1つ前の文字と一致するか確認するが、先頭の要素の場合は確認不要のため、変数iの値が0より大きい場合にだけ比較を行う
            //※ &&演算子では最初の式がfalseになる場合は後の式を実行しない
                if(i > 0 && ch == data.charAt(i - 1)){
                    //1つ前の文字と同じだった場合には、continueにより以降の処理をスキップし、次の文字の処理（i++して14行目）に進む
                    continue;
                    }
                //先頭の文字か、1つ前の文字と異なる場合にStringBuilderオブジェクトにappendメソッドにより文字を追加する
            builder.append(ch);
        }
        //すべての文字が終わったらtoStringメソッドでStringBuildderオブジェクトに構築された文字列を取得
        var result = builder.toString();
        System.out.println(data);
        System.out.println(result);
    }
}
