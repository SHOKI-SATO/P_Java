package projava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @Test
    void add() {
//        アサーションメソッド(実行結果が期待した値になることを確認・検証するメソッド)
//       「　assertEquals(期待値, 実際の値 [, テスト内容を表すメッセージ])　」
//        →「実際の値」で表現する式を評価した結果が「期待値」と等しい場合にテストが成功（等しくない場合は「AssertionFailedError」というエラーが投げられる）
        assertEquals(4, new Calc().add(2, 2), "2 + 2 = 4");
//        assertEquals(6, new Calc().add(2, 4), "2 + 4 = 8");
    }
}