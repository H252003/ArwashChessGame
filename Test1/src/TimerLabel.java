import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TimerLabel extends JLabel {


    public static boolean whiteTurn = true;
    private Timer timer;

    public int getSecond() {
        return second;
    }
    public int getMinute(){
        return minute;
    }
    public static int clock;

    public int second;
    public int minute;
    private String ddSecond, ddMinute;
    private DecimalFormat dFormat = new DecimalFormat("00");

    public TimerLabel()
    {

    }
    public TimerLabel(JLabel timerLabel, String time) {
        super();
        clock = Integer.parseInt(time);
//        second = clock;
        countdownTimer(timerLabel,clock);
        timer.start();
    }

    public void countdownTimer(JLabel timerLabel, int time) {
        minute=time;
        second=00;


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                setText(ddMinute + ":" + ddSecond);

                if(second == -1) {
                    second = 59;
                    minute--;}
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    timerLabel.setText(ddMinute + ":" + ddSecond);
                    //System.out.println(ddMinute);
                    //System.out.println(ddSecond);

                if(minute == 0 && second == 0 && !game.gameEnded) {
                    timer.stop();
                    minute = 0;
                    second = 0;
                    game.endGame(!TimerLabel.whiteTurn,"endTime");}
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
    public static String elapsedTime(TimerLabel Timer1, TimerLabel Timer2){
//        Timer1.stopTimer();
//        Timer2.stopTimer();
        int minuteInit = clock;
        int secondInit = 00;
        int minuteFinal1 = Timer1.getMinute();
        int secondFinal1= Timer1.getSecond();
        int minuteFinal2 = Timer2.getMinute();
        int secondFinal2 = Timer2.getSecond();
        int minutes1 = minuteInit - minuteFinal1;
        int seconds1 = secondInit - secondFinal1;
        if (seconds1 < 0){
            seconds1 = seconds1 + 60;
            minutes1--;
        }
        int minutes2 = minuteInit - minuteFinal2;
        int seconds2 = secondInit - secondFinal2;
        if (seconds2 < 0){
            seconds2 = seconds2 + 60;
            minutes2--;
        }
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