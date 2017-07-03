package com.zach.salman.springy;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zach.salman.springylib.SpringAnimationType;
import com.zach.salman.springylib.SpringyAnimator;

import java.util.ArrayList;
import java.util.List;

public class MediaActivity extends AppCompatActivity {

    private static final double TRANS_TENSION = 15;
    private static final double TRANS_FRACTION = 5;
    View
            image,
            gradient,
            layer1,
            layer2,
            title,
            heart,
            artist,
            forward,
            rewind,
            repeat,
            random,
            musicBar;
    ImageView play;
    private List<View> views = new ArrayList<>();

    private static final int DELAY = 100;
    private boolean isPlay =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        init();
    }




    private void init(){
        image = findViewById(R.id.image);
        gradient = findViewById(R.id.gradient);
        title = findViewById(R.id.title);
        artist = findViewById(R.id.artist);
        heart = findViewById(R.id.heart);
        layer1 = findViewById(R.id.layer1);
        layer2 = findViewById(R.id.layer2);
        play = (ImageView) findViewById(R.id.play);
        forward = findViewById(R.id.forward);
        rewind = findViewById(R.id.rewind);
        repeat = findViewById(R.id.repeat);
        random = findViewById(R.id.random);
        musicBar = findViewById(R.id.music_bar);

        views.add(layer1);
        views.add(layer2);
        views.add(play);

        setTranslate(image,DELAY*2);
        setTranslate(gradient,DELAY*3);
        setTranslate(title,DELAY*7);
        setTranslate(artist,DELAY*8);
        setTranslate(forward,DELAY*11);
        setTranslate(rewind,DELAY*11);

        setScale(layer1,DELAY*10);
        setScale(layer2,DELAY*11);
        setScale(play,DELAY*12);
        setScale(repeat,DELAY*16);
        setScale(random,DELAY*17);
        setScale(heart,DELAY*18);
        setScale(musicBar,DELAY*12);

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateView(view);
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateView(view);
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateView(view);
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAnimate();
            }
        });

    }


    private void animateView(View view){
        SpringyAnimator springHelper = new SpringyAnimator(SpringAnimationType.SCALEXY,100,4,0,1);
        springHelper.startSpring(view);
    }


    private void playAnimate(){
        final SpringyAnimator springHelper = new SpringyAnimator(SpringAnimationType.SCALEXY,100,3,0.5f,1);
        for (int i = 0; i <views.size() ; i++) {
            final int count = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    springHelper.startSpring(views.get(count));
                    if (count == 2){
                        if (!isPlay){
                            play.setImageResource(R.drawable.ic_pause_black_24dp);
                            isPlay =true;
                        }else {
                            play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                            isPlay =false;
                        }
                    }
                }
            },80*i);

        }
    }


    private void setScale(View view, int delay){
        SpringyAnimator helper = new SpringyAnimator(SpringAnimationType.SCALEXY, 15,4,0,1);
        helper.setDelay(delay);
        helper.startSpring(view);

    }

    private void setTranslate(View view, int delay){

        switch (view.getId()){
            case R.id.image:
                SpringyAnimator s1 = new SpringyAnimator(SpringAnimationType.ALPHA, 10, 5,0,1);
                s1.setDelay(delay);
                s1.startSpring(view);
                break;
            case R.id.gradient:
                SpringyAnimator s2 = new SpringyAnimator(SpringAnimationType.TRANSLATEY, 40, 10,getResources().getDisplayMetrics().heightPixels,0);
                s2.setDelay(delay);
                s2.startSpring(view);
                break;
            case R.id.title:
                SpringyAnimator s3 = new SpringyAnimator(SpringAnimationType.TRANSLATEY, TRANS_TENSION, TRANS_FRACTION,getResources().getDisplayMetrics().heightPixels/8,0);
                s3.setDelay(delay);
                s3.startSpring(view);
                break;
            case R.id.artist:
                SpringyAnimator s4 = new SpringyAnimator(SpringAnimationType.TRANSLATEY, TRANS_TENSION, TRANS_FRACTION,getResources().getDisplayMetrics().heightPixels/8,0);
                s4.setDelay(delay);
                s4.startSpring(view);
                break;
            case R.id.forward:
                SpringyAnimator s5 = new SpringyAnimator(SpringAnimationType.TRANSLATEX, 8, 3,-play.getLayoutParams().width,0);
                s5.setDelay(delay);
                s5.startSpring(view);
                break;

            case R.id.rewind:
                SpringyAnimator s6 = new SpringyAnimator(SpringAnimationType.TRANSLATEX, 8, 3,play.getLayoutParams().width,0);
                s6.setDelay(delay);
                s6.startSpring(view);
                break;
        }
    }
}
