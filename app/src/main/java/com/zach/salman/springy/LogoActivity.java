package com.zach.salman.springy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zach.salman.springylib.SpringAnimationType;
import com.zach.salman.springylib.SpringyAnimator;

public class LogoActivity extends AppCompatActivity  {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        logo = (ImageView) findViewById(R.id.logo);


        final SpringyAnimator scaleY = new SpringyAnimator(SpringAnimationType.SCALEY,5,3,0.5f,1);
        final SpringyAnimator rotate = new SpringyAnimator(SpringAnimationType.ROTATEY,5,3,180,0);
        rotate.setDelay(100);
        scaleY.setDelay(200);
        rotate.startSpring(logo);
        scaleY.startSpring(logo);



    }

}
