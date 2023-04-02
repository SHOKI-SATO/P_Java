package jp.gihyo.projava.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//「@RestController」ではなく「@Controller」を使ったっ場合は、レスポンスとしてサーバ側のプログラム内でWebコンテンツを生成して返す
//　→メソッドの戻り値として、「ビューを表すオブジェクト（レスポンス本体のHTML文書を生成するオブジェクト）の名称」を返すのがデフォルトの挙動()
@Controller
public class HomeController {

    @RequestMapping("/hello")
    String hello(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }

    record TaskItem(String id, String task, String deadline, boolean done) {
    }

    private List<TaskItem> taskItems = new ArrayList<>();

    //    TaskListDaoクラスのフィールドと、それを初期化するためのコンストラクタを追加
    private final TaskListDao dao;

    @Autowired
    HomeController(TaskListDao dao) {
        this.dao = dao;
    }


    @GetMapping("/list")
    String listItems(Model model) {
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("tasklist", taskItems);
        return "home";
    }

    //    「/add」はhome.htmlのフォームのリクエスト先と同じ
    @GetMapping("/add")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        dao.add(item);
//        「redirect：●●」とすることで、表示するWebページを指定のパスにリダイレクト（転送）する
        return "redirect:/list";
    }

    //    パラメータとして渡されたタスクIDを引数にして、TaskListDaoクラスのdeleteメソッドを呼び出す
//    削除が出来たら、addメソッドと同様に「/list」 にリダイレクト
    @GetMapping("/delete")
    String deleteItem(@RequestParam("id") String id) {
        dao.delete(id);
        return "redirect:/list";
    }

    //     受け取ったパラメータを元にTaskItemオブジェクトを作成し、それを引数としてTaskListDaoのupdateメソッドを呼び出す
//     addItemメソッドではタスクのIDは自動で新期に生成していたが、今回は既存のタスク情報を更新したいため、IDを含めた全部の項目の値をパラメータで受け取る
    @GetMapping("/update")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("task") String task,
                      @RequestParam("deadline") String deadline,
                      @RequestParam("done") boolean done) {
        TaskItem taskItem = new TaskItem(id, task, deadline, done);
        dao.update(taskItem);
        return "redirect:/list";
    }


//    @ResponseBody
//    String hello(){
//        return """
//                <html>
//                <head><title>Hello</title></head>
//                <body>
//                <h1>Hello</h1>
//                It works!<br>
//                現在時刻は%sです。
//                </body>
//                </html>
//                """.formatted(LocalDateTime.now());
//    }
}
