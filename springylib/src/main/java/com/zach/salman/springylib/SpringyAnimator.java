package com.zach.salman.springylib;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;


/**
 * Created by salman on 17/11/16.
 */

public class SpringyAnimator {
    private float startValue, endValue;
    private  static final float DEFAULT_TENSION = 40;
    private  static final float DEFAULT_FRACTION = 7;
    private double tension,fraction;
    private SpringSystem springSystem;
    private SpringAnimationType animationType;
    private SpringyListener springAnimatorListener;
    private int delay = 0;


    /**
     * Constructor for with Animation Type + Spring config + animation Values
     * * @param springConfig config class for the spring
     * @param  type SpringyAnimationType instance for animation type
     * @param  tension Spring tension for animation type
     * @param  fraction Spring fraction value for animation
     * @param  startValue where animation start from
     * @param  endValue where animation ends to
     * **/


    public SpringyAnimator(SpringAnimationType type, double tension,
                           double fraction, float startValue, float endValue) {
        this.tension = tension;
        this.fraction = fraction;
        this.startValue = startValue;
        this.endValue = endValue;
        springSystem = SpringSystem.create();
        animationType = type;

    }



    /**
     * Constructor for with Animation Type + default config for spring + animation Values
     * * @param springConfig config class for the spring
     * @param  type SpringyAnimationType instance for animation type
     * @param  startValue where animation start from
     * @param  endValue where animation ends to
     * **/
      public SpringyAnimator(SpringAnimationType type, float startValue,
                             float endValue) {
        this.tension = DEFAULT_TENSION;
        this.fraction = DEFAULT_FRACTION;
        this.startValue = startValue;
        this.endValue = endValue;
        springSystem = SpringSystem.create();
        animationType = type;

    }




    /**
     * @param  delay   int value  for SpringyAnimation delay each item if we have multiple items in
     *                 animation  raw.
     * **/
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void startSpring(final View view) {
        setInitValue(view);
        Runnable startAnimation = new Runnable() {
            @Override
            public void run() {
                Spring spring = springSystem.createSpring();
                spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, fraction));
                spring.addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        view.setVisibility(View.VISIBLE);
                        final float value = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, startValue, endValue);
                        switch (animationType) {
                            case TRANSLATEY:
                                view.setTranslationY(value);
                                break;
                            case TRANSLATEX:
                                view.setTranslationX(value);
                                break;
                            case ALPHA:
                                view.setAlpha(value);
                                break;
                            case SCALEY:
                                view.setScaleY(value);
                                break;
                            case SCALEX:
                                view.setScaleX(value);
                                break;
                            case SCALEXY:
                                view.setScaleY(value);
                                view.setScaleX(value);
                                break;
                            case ROTATEY:
                                view.setRotationY(value);
                                break;
                            case ROTATEX:
                                view.setRotationX(value);
                                break;
                            case ROTATION:
                                view.setRotation(value);
                                break;
                        }
                    }

                    @Override
                    public void onSpringAtRest(Spring spring) {
                        if (springAnimatorListener != null){
                            springAnimatorListener.onSpringStop();
                        }
                    }

                    @Override
                    public void onSpringActivate(Spring spring) {
                        if (springAnimatorListener != null){
                            springAnimatorListener.onSpringStart();
                        }
                    }

                });
                spring.setEndValue(1);
            }
        };
        view.postDelayed(startAnimation, delay);

    }

    /**
     * @param  view  instance for  set pre animation value
     * **/
    private void setInitValue(View view) {
        switch (animationType) {
            case TRANSLATEY:
                view.setTranslationY(startValue);
                break;
            case TRANSLATEX:
                view.setTranslationX(startValue);
                break;
            case ALPHA:
                view.setAlpha(startValue);
                break;
            case SCALEY:
                view.setScaleY(startValue);
                break;
            case SCALEX:
                view.setScaleX(startValue);
                break;
            case SCALEXY:
                view.setScaleY(startValue);
                view.setScaleX(startValue);
                break;
            case ROTATEY:
                view.setRotationY(startValue);
                break;
            case ROTATEX:
                view.setRotationX(startValue);
                break;
            case ROTATION:
                view.setRotation(startValue);
                break;
        }
    }

/*
* Springy Listener to track the Spring
* */
    public void setSpringyListener(SpringyListener springyListener) {
        this.springAnimatorListener = springyListener;
    }


}
