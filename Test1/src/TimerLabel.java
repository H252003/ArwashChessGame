import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TimerLabel extends JLabel {

    private Timer timer;
    private int second, minute;
    private String ddSecond, ddMinute;
    private DecimalFormat dFormat = new DecimalFormat("00");

    public TimerLabel(JLabel timerLabel, String time) {
        super();
        int clock = Integer.parseInt(time);
        second = clock;
        countdownTimer(timerLabel,clock);
        timer.start();
    }

    public void countdownTimer(JLabel timerLabel, int time) {
        minute=time/60;
        second=time%60;
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
                    System.out.println(ddMinute);
                    System.out.println(ddSecond);

                if(minute == 0 && second == 0) {
                    timer.stop();
                    // do something when the timer reaches zero
                }
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }



    // add other methods as needed
}