package com.example.whsys7.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       seekBar = (SeekBar) findViewById(R.id.progressBar);
        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        seekBar.setProgress(20);
        seekBar.setMax(60);
        textView.setText("00:"+checkNumber(seekBar.getProgress()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("00:"+checkNumber(progress));
               // seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public String checkNumber(int number){
        if (number <=9)
            return (Integer.toString(0)+ Integer.toString(number));

        else
            return  Integer.toString(number);
    }

    public void updateTime(int secsLeft) {

        int minute = (int)secsLeft/60;

     int secs = secsLeft - minute*60;
        textView.setText(checkNumber(minute)+":"+checkNumber(secs));
    }

    public void start(View view){
        seekBar.setEnabled(false);
        button.setEnabled(false);
        //final int number = seekBar.getProgress();
        new CountDownTimer(seekBar.getProgress()*1000+100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                int count = (int)millisUntilFinished/1000;
              updateTime(count);
              seekBar.setProgress(count);
            }

            @Override
            public void onFinish() {
               textView.setText("00:00");
                seekBar.setEnabled(true);
                button.setEnabled(true);
                playMedia(R.raw.tolling);
            }
        }.start();
    }


    public void playMedia(int id){
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), id);
        mediaPlayer.start();
    }
}
