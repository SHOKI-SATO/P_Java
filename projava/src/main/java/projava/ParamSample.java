package projava;

import java.util.Arrays;

//複数のアウタークラスを１つのソースファイルに書くことはできるが、publicなアウタークラスはファイル名と同名の一つのみである必要がある
//→　ParamSampleクラスとDummyクラスがあるが、publicなのはParamSampleクラスなので、ParamSample.javaというファイル名で保存する必要がある

/**
プログラム開始時に呼び出される
@Param args コマンドライン引数
*/
public class ParamSample {
    public static void main(String[] args) {
        //引数の内容を表示（配列は直接表示できないのでArraysメソッドを使う）
        System.out.println(Arrays.toString(args));
    }
}

/*
public ではないアウタークラス
複数のアウタークラスを一つのファイルに定義することを示すため
*/
class Dummy{

}



