package com.KeltisT.Game;

public class GameTimer {
    private static int seconds = 60;

    /*@FXML
    public static Label TimerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TimerLabel.setText("01:00");
    }

    public static String initializee(String timer) {
        System.out.println(timer);
        String[] test = {timer};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e-> {
            seconds--;
            if (seconds >= 10){
                test[0] = "00:" + seconds;
            }
            else if(seconds < 10) {
                test[0] = "00:0" + seconds;
            }
            if(seconds == 0) {
                test[0] = "01:00";
                seconds = 60;
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        return test[0];

    } */

}
