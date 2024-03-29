package projava;

import java.util.List;

public class InheritSample {
    static class User {
        String name;

        public String getName() {
            return name;
        }
    }

    static class Student extends User {
        int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        static class Teacher extends User {
            String subject;

            Teacher(String name, String subject) {
                this.name = name;
                this.subject = subject;
            }

            public String getSubject() {
                return subject;
            }
        }

        public static void main(String[] args) {
            //Userオブジェクトを持つListとしているが、継承の場合は「サブクラスはスーパークラスの一種（is-a関係）」となるので、サブクラスであるStudentクラスとTeacherクラスも格納できる
            List<User> people = List.of(new Student("kis", 80), new Teacher("hosoya", "Math"));
            for (var p : people) {
                System.out.println("こんにちは%sさん".formatted(p.getName()));
            }
        }
    }
}

