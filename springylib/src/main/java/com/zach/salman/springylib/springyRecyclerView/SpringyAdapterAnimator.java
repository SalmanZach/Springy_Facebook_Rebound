package com.zach.salman.springylib.springyRecyclerView;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;


/**
 * Created by Zach on 6/30/2017.
 */

public class SpringyAdapterAnimator {

    private static final int INIT_DELAY = 100;

    private static final int INIT_TENSION = 200;
    private static final int INIT_FRICTION = 20;

    private int tension;
    private int fraction ;


    private static final int PER_ITEM_GAPE = 100;

    private int parentHeight;
    private int parentWidth;
    private RecyclerView parent;
    private SpringSystem mSpringSystem;
    private SpringyAdapterAnimationType animationType;
    private boolean mFirstViewInit = true;
    private int mLastPosition = -1;
    private int mStartDelay;

    public SpringyAdapterAnimator(RecyclerView recyclerView) {
        parent = recyclerView;
        mSpringSystem = SpringSystem.create();
        animationType = SpringyAdapterAnimationType.SLIDE_FROM_BOTTOM;
        parentHeight = parent.getResources().getDisplayMetrics().heightPixels;
        parentWidth = parent.getResources().getDisplayMetrics().widthPixels;
        mStartDelay = INIT_DELAY;
        tension = INIT_TENSION;
        fraction= INIT_FRICTION;
    }
/*
* setInitDelay @param initDelay for set delay at screen creation
* */
    public void setInitDelay(int initDelay){
        mStartDelay = initDelay;
    }

    public void setSpringAnimationType(SpringyAdapterAnimationType type){
        animationType = type;
    }


    public void addConfig(int tension,int fraction){
        this.tension = tension;
        this.fraction = fraction;
    }

    /**
     * onSpringyItemCreate call in Adapter's Constructor method
     * @param item itemView instance from RecyclerView's OnCreateView method
     * **/
    public void onSpringItemCreate(View item) {

        if (mFirstViewInit) {
            setAnimation(item, mStartDelay, tension, fraction);
            mStartDelay += PER_ITEM_GAPE;
        }
    }


    /**
     * * onSpringyItemBind call in RecyclerView's onBind for scroll effects
     * @param  item itemView instance from RecyclerView's onBind method
     * @param  position from RecyclerView's onBind method
     * **/
    public void onSpringItemBind(View item, int position) {

        if (!mFirstViewInit && position > mLastPosition) {
            setAnimation(item, 0, tension - tension/4, fraction);
            mLastPosition = position;
        }
    }


    private void setAnimation(final View item, final int delay,
                              final int tension, final int friction) {
        setInitValue(item);
        Runnable startAnimation = new Runnable() {
            @Override
            public void run() {
                SpringConfig config = new SpringConfig(tension, friction);
                Spring spring = mSpringSystem.createSpring();
                spring.setSpringConfig(config);
                spring.addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        switch (animationType) {
                            case SLIDE_FROM_BOTTOM:
                                item.setTranslationY(getMappedValue(spring));
                                break;
                            case SLIDE_FROM_LEFT:
                                item.setTranslationX(getMappedValue(spring));
                                break;
                            case SLIDE_FROM_RIGHT:
                                item.setTranslationX(getMappedValue(spring));
                                break;
                            case SCALE:
                                item.setScaleX(getMappedValue(spring));
                                item.setScaleY(getMappedValue(spring));
                                break;
                        }

                    }

                    @Override
                    public void onSpringEndStateChange(Spring spring) {
                        mFirstViewInit = false;
                    }
                });
                spring.setEndValue(1);

            }
        };

        parent.postDelayed(startAnimation, delay);
    }

    private void setInitValue(View item) {

        switch (animationType) {
            case SLIDE_FROM_BOTTOM:
                item.setTranslationY(parentHeight);
                break;
            case SLIDE_FROM_LEFT:
                item.setTranslationX(-parentWidth);
                break;
            case SLIDE_FROM_RIGHT:
                item.setTranslationX(parentWidth);
                break;
            case SCALE:
                item.setScaleX(0);
                item.setScaleY(0);
                break;
            default:
                item.setTranslationY(parentHeight);
                break;
        }
    }

    private float getMappedValue(Spring spring) {

        float value;
        switch (animationType) {
            case SLIDE_FROM_BOTTOM:
                value = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, parentHeight, 0);
                break;
            case SLIDE_FROM_LEFT:
                value = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, -parentWidth, 0);
                break;
            case SLIDE_FROM_RIGHT:
                value = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, parentWidth, 0);
                break;
            case SCALE:
                value = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0, 1);
                break;
            default:
                value = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, parentHeight, 0);
                break;
        }
        return value;
    }

}
