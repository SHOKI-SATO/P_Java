package projava;

public class JavadocSample {

//  以下のJavadocを作成したら、Ctrl + Q(Ctrl + J)でJavadocを確認出来る
    /**
     *渡された西暦年が夏季オリンピック開催年であるかどうか判定する
     * @param year 西暦年
     * @return 夏季オリンピック開催年であればtrue
     * @throws IllegalArgumentException まだオリンピック開催が確定していない年を渡した場合
     */
    public boolean isSummerOlympicYear(int year)throws IllegalArgumentException{
        if(2032 < year){
            throw new IllegalArgumentException("2023年までをサポートしています。入力：" + year);
        }
        return year % 4 == 0;
    }

}
