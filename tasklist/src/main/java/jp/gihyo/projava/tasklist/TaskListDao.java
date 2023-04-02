package jp.gihyo.projava.tasklist;

import jp.gihyo.projava.tasklist.HomeController.TaskItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterInputStream;

//        Dao・・・Date Access Object、DBにアクセスするための窓口となるオブジェクト
@Service
public class TaskListDao {
    private final JdbcTemplate jdbcTemplate;

//    「@Autowired」をつけることで、SpringBootアプリケーションはTaskListDaoっクラスのコンストラクタを呼び出す際に引数として適切なオブジェクトを作成して渡す様になる
//            →　自前ｄjdbcTemplateの初期設定をしてTaskListDaoのコンストラクタに渡す必要がない(DI:依存性の注入)
//        jdbcTemplateは実際にDBへのアクセスを担当するクラスであるため、DBの接続先や接続方法などの情報を保持している必要がある
//            (これらの情報はapplication.propatiesに記述されている)
//        →　「@Autowired」を付与することで、それらの設定内容を反映したjdbcTemplateオブジェクトを自動で作成してくれる様になる

    @Autowired
    TaskListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //    引数に渡されたTaskItemオブジェクトの情報をDBのtasklistテーブルに登録
    public void add(TaskItem taskItem) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(taskItem);
//        「SimpleJdbcInsertクラス」・・・SpringJDBCに用意されたメソッド、SQL（INSERT文）発行の代わりにexecuteメソッドを呼び出すだけでデータの追加が可能（手順はやや複雑）
//        　手順① SqlParameterSourceクラスのオブジェクトを用意
//        　　　　　　BeanPropertySqlParameterSourceはSqlParameterSourceの実装クラス、コンストラクタにはテーブルに追加したいデータを表すクラスのオブジェクトを渡す（taskItem）
//        　手順②　SimpleJdbcInsertのコンストラクタには、対象となるDBに紐づいているjdbcTemplateを渡す
//        　手順③　withTablNameメソッドを呼び出してデータを追加する対象のテーブル名を設定
//    　　　 手順④　SqlParameterSourceオブジェクトをexecuteメソッドに渡す　→内部で自動的にSQLを発行してテーブルにデータが追加される
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tasklist");
        insert.execute(param);
    }

    //    tasklistテーブルから現在登録されてpいるタスク情報を全て取得して、Listオブジェクトに格納して返すメソッド
    public List<TaskItem> findAll() {
        String query = "SELECT * FROM tasklist";
//        jdbcTemplateクラスにはSELECT文を組み立てるのに使用できるメソッドがいくつかあり、queryForListメソッドはそのうちの一つ
//        queryForListメソッド・・・SQL文をStringとして引数に渡すと、検索結果をListで返却する（キー：列名、バリュー：データ　となるMapで返却）
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
//        queryForListの戻り値から要素を一つずつ取りだし、それぞれTaskItemsオブジェクトを作成してからListに格納
        List<TaskItem> taskItems = result.stream()
                .map((Map<String, Object> row) -> new TaskItem(
                        row.get("id").toString(),
                        row.get("task").toString(),
                        row.get("deadline").toString(),
                        (Boolean) row.get("done")))
                .toList();

        return taskItems;
    }

    //    引数に削除対象となるタスク情報のIDを受けとり、そのIDのレコードを削除するためのDELETE文を発行
    public int delete(String id) {
//        jdbcTemplateを使ってレコードの削除や更新を行うにはupdateメソッドを使用する
        int number = jdbcTemplate.update("DELETE FROM tasklist WHERE id = ?", id);
        return number;
    }

    //    引数に更新したいタスク情報をTaskItemオブジェクトとして受け取り、deleteメソッドと同様にその内容を使ってUPDATE文を作成し、jdbcTemplateのupdateメソッドを使ってDBに発行
//    戻り値は、updateメソッドから返された更新されたレコードの行数
    public int update(TaskItem taskItem) {
        int number = jdbcTemplate.update("UPDATE tasklist SET task = ?, deadline = ?, done = ? WHERE id = ?",
                taskItem.task(),
                taskItem.deadline(),
                taskItem.done(),
                taskItem.id());
        return number;
    }
}
