package projava;

//状態遷移の管理とenum
public class CheckFloat {
    //状態を表す値をenumとして用意
    //　→enumは、プログラムで利用する値をまとめて扱えるようにする型
    //FRACは少数部を表すFractionの略
    //START＝整数の先頭、INT＝整数、FRAC_START＝少数の先頭。FRAC＝少数の途中、ZERO＝0、
    enum FloatState {
        START, INT, FRAC_START, FRAC, ZERO
    }
    static boolean check(String data){
        //状態を表す変数stateを用意（初期状態はSTART）
        var state = FloatState.START;
        //一文字ずつ繰り返す
        for(char ch : data.toCharArray()){
            //状態によって変わるよう,switch文を使う
            switch(state){
                //状態遷移図のとおりにstateの値が変化するように
                //状態遷移先のない文字の場合はfalse
                case START -> {   //開始
                    if(ch == '0'){
                        state = FloatState.ZERO;
                } else if (ch >= '1' && ch <= '9') {
                        state = FloatState.INT;
                    }else{
                        return  false;
                    }
                }
                case ZERO -> {  //先頭がゼロ
                    if (ch == '.'){
                        state = FloatState.FRAC_START;
                    }else{
                        return false;
                    }
                }
                case INT ->{ //　整数
                    if(ch >= '0' && ch <= '9'){
                        state = FloatState.INT;
                    }else if (ch == '.') {
                        state = FloatState.FRAC_START;
                    }else {
                        return false;
                    }
                }
                case FRAC_START, FRAC -> {  //小数部
                    if(ch >= '1' && ch <= '9'){
                        state = FloatState.FRAC;
                    }else {
                        return false;
                    }
                }
            }
        }
        //すべての文字を処理したときに、行末への遷移が可能な状態であればtrue、それ以外はfalseを返し、判定終了
        return switch (state){
            case ZERO, INT, FRAC ->true;
            default -> false;
        };
    }

    public static void main(String[] args) {
        System.out.println(check(""));  //false
        System.out.println(check("012"));  //false
        System.out.println(check(".12"));  //false
        System.out.println(check("12."));  //false
        System.out.println(check("1.2.3"));  //false
        System.out.println(check("1..3"));  //false
        System.out.println(check("0"));  //true
        System.out.println(check("12"));  //true
        System.out.println(check("12.3"));  //true
        System.out.println(check("0.3"));  //true
        System.out.println(check("12.30"));  //true

    }
}
