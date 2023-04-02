package projava;

public class TotalScoreLevel{
        public static void main(String[] args) throws Exception {

            int totalScore = 100;
            int rank = 0;
            if(totalScore == null){
                rank = -1;
            }else if(totalScore >= 80 && totalScore <= 100){
                rank = 8;
            }else if(totalScore >= 60 && totalScore <80){
                rank = 4;
            }else if(totalScore >= 0 && totalScore <60){
                rank = 0;
            }else{
                rank = -1;
            }

            return rank;
        }

}

