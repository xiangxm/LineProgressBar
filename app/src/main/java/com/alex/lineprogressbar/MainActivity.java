package com.alex.lineprogressbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.socks.library.KLog;

import github.alex.progressview.LineProgressView;

public class MainActivity extends AppCompatActivity {
    private LineProgressView lineProgressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        lineProgressView = (LineProgressView) findViewById(R.id.lpv);
        lineProgressView.setMaxProgressColor(Color.parseColor("#F1F1F1"));
        lineProgressView.setFirstProgressColor(Color.parseColor("#C5C5C5"));
        lineProgressView.setFirstProgress(60F);
        lineProgressView.setSecondProgressColor(Color.parseColor("#FF5722"));
        lineProgressView.setSecondProgress(40F);
        lineProgressView.setRadius(20, 40);
        //
        SeekBar seekBar1 = (SeekBar)findViewById(R.id.sb_1);
        SeekBar seekBar2 = (SeekBar)findViewById(R.id.sb_2);
        seekBar1.setProgress(60);
        seekBar2.setProgress(40);
        seekBar1.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());
        seekBar2.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());
    }
    private final class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(R.id.sb_1 == seekBar.getId()){
                lineProgressView.setFirstProgress(seekBar.getProgress());
            }else  if(R.id.sb_2 == seekBar.getId()){
                lineProgressView.setSecondProgress(seekBar.getProgress());
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
