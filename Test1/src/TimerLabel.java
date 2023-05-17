import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TimerLabel extends JLabel {


    public static boolean whiteTurn = true;
    private Timer timer; //object of class timer

    public int getSecond() {
        return second;
    }
    public int getMinute(){
        return minute;
    }
    public static int clock; //initial time value

    public int second;
    public int minute;
    private String ddSecond, ddMinute; //Decimal format of second/minute
    private DecimalFormat dFormat = new DecimalFormat("00"); //The traditional format of a timer

    public TimerLabel()
    {

    }
    public TimerLabel(JLabel timerLabel, String time) {
        super();
        clock = Integer.parseInt(time); //turn String time into integer
        countdownTimer(timerLabel,clock);
        timer.start();
    }

    public void countdownTimer(JLabel timerLabel, int time) {
        minute=time;
        second=00;


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //when the player's turn starts

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                timerLabel.setText(ddMinute + ":" + ddSecond); //update timer every second

                if(second == -1) {
                    second = 59;
                    minute--;}
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    timerLabel.setText(ddMinute + ":" + ddSecond);

                if(minute == 0 && second == 0 ) {//&& !game.gameEnded
                    timer.stop();
                    minute = 0;
                    second = 0;
                    game.endGame(TimerLabel.whiteTurn,"endTime");}
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
    public static String elapsedTime(TimerLabel TimerB, TimerLabel TimerW){
        //initial time
        int minuteInit = clock;
        int secondInit = 00;
        //time for black player
        int minuteFinal1 = TimerB.getMinute();
        int secondFinal1= TimerB.getSecond();
        //time for white player
        int minuteFinal2 = TimerW.getMinute();
        int secondFinal2 = TimerW.getSecond();
        //elapsed time for black player
        int minutes1 = minuteInit - minuteFinal1;
        int seconds1 = secondInit - secondFinal1;
        //handle negatives
        if (seconds1 < 0){
            seconds1 = seconds1 + 60;
            minutes1--;
        }
        //elapse time for white player
        int minutes2 = minuteInit - minuteFinal2;
        int seconds2 = secondInit - secondFinal2;
        //handle negatives
        if (seconds2 < 0){
            seconds2 = seconds2 + 60;
            minutes2--;
        }
        //sum of elapsed time for both players
        int minute = minutes1 + minutes2;
        int second = seconds1 + seconds2;
        if (second > 60) {
        second= second -60;
        minute++;
        }
       DecimalFormat dFormat = new DecimalFormat("00");
       String ddSecond = dFormat.format(second);
       String ddMinute = dFormat.format(minute);
        return (ddMinute + ":" + ddSecond);

    }



    // add other methods as needed
}