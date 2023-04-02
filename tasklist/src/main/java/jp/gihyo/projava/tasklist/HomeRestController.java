package jp.gihyo.projava.tasklist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//  クラスに対して「@Controller」または「@RestController」のいずれかのアノテーションを付与するだけで
// 「このクラスはコントローラ」と認識して、HTTPに関する様々な処理を自動で行なってくれる
@RestController
public class HomeRestController {
    record TaskItem(String id, String task, String deadline, boolean done) {
    }

    private List<TaskItem> taskItems = new ArrayList<>();


    //    「@RequestMapping  」は、クライアントからのリクエストを処理するメソッドであることを表すアノテーション
//    value属性に指定した値は、URLのどのパスに対するリクエストがこのメソッドで処理されるかを表す（value属性の指定に限り、「value =」は省略可能）
//    →　つまり、helloメソッドはクライアントから何らかのリクエストが送られてきた場合に呼び出されるメソッド
//  　  「http://www.example.com/」というURLのサイトだった場合、「http://www.example.com/hello」に対してリクエストが送られた場合に呼び出される
    @RequestMapping(value = "/resthello")
    String hello() {
        return """
                Hello.
                It works!
                現在時刻は%sです。
                """.formatted(LocalDateTime.now());
    }


    //    「@RequestMapping」はGETとPOSTの両方に対応しているが、「@GetMapping」はGETのみ対応している
//    「@RequestParam」アノテーションが付けられた引数は、自動的にHTTPリクエストのパラメータと関連づけられる
//    　→「/restadd」エンドポイントにHTTPリクエストが送られるとaddItemsメソッドが呼び出される
//    　　　そして、そのリクエストの「taskパラメータ」と「deadlineパラメータ」の値がaddItemsメソッド呼び出しの際に引数として渡される
    @GetMapping("/restadd")
    String addItems(@RequestParam("task") String task,
                    @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);
        return "タスクを追加しました。";
    }


    @GetMapping("/restlist")
    String listItems() {
        String result = taskItems.stream()
                .map(TaskItem::toString)
                .collect(Collectors.joining(","));
        return result;
    }

}
