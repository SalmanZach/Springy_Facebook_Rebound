package com.zach.salman.springy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.zach.salman.springylib.SpringAnimationType;
import com.zach.salman.springylib.SpringyAnimator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        views.add(findViewById(R.id.text1));
        views.add(findViewById(R.id.text2));
        views.add(findViewById(R.id.text3));

        /*
        * animation for main screen logo
        * */
        SpringyAnimator iconSpring = new SpringyAnimator(SpringAnimationType.SCALEXY, 4, 2.5, 0, 1);
        SpringyAnimator iconRotateSpring = new SpringyAnimator(SpringAnimationType.ROTATION, 10, 1.5, 180, 0);
        iconSpring.setDelay(200);
        iconRotateSpring.setDelay(200);
        iconSpring.startSpring(findViewById(R.id.icon));
        iconRotateSpring.startSpring(findViewById(R.id.icon));

        /*
        * animation spring for tabs on main screen
        * */
        final SpringyAnimator spring = new SpringyAnimator(SpringAnimationType.TRANSLATEY, 10, 5, getResources().getDisplayMetrics().heightPixels, 0);
        for (int i = 0; i < views.size(); i++) {
            final int count = i;
            views.get(i).postDelayed(new Runnable() {
                @Override
                public void run() {
                    spring.startSpring(views.get(count));
                }
            },100*i);
        }


        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MediaActivity.class));
            }
        });

        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        findViewById(R.id.text3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LogoActivity.class));
            }
        });
    }


}