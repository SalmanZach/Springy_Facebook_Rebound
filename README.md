# Springy_Facebook_Rebound


![ic_launcher](https://user-images.githubusercontent.com/11782272/27817284-ec04cbb0-60ad-11e7-901e-88e261b60c86.png)

  Springy is About Motion and Animation on Android platform, you can make smooth and clean property animations
### Scale, Translate, Alpha, Rotate.

![media player](https://user-images.githubusercontent.com/11782272/27817251-c255cff8-60ad-11e7-8e39-4e1c5eda1865.gif)    



## How to use

   Create Springy Animator instance and you can initialize by one of it's two constructors. 
     
     with Spring Config.
     
     1.SpringyAnimator(SpringAnimationType.SCALEXY,TENSION, FRACTION, ANIMATION_START_VALUE, ANIMATION_END_VALUE); 
     
     Without Spring Config.
     
     2.SpringyAnimator(SpringAnimationType.SCALEXY, ANIMATION_START_VALUE, ANIMATION_END_VALUE);
     
            {
            
            SpringyAnimator iconSpring = new SpringyAnimator(SpringAnimationType.SCALEXY, 4, 2.5, 0, 1);
            
            // start spring with you view.            
            iconSpring.startSpring(myView);      
            }
    
you can set Delay.
                    
                    iconSpring.setDelay(200);
                    
SpringyAnimationTypes you can use.

    TRANSLATEX,
    TRANSLATEY,
    ROTATEX,
    ROTATEY,
    SCALEXY,
    SCALEX,
    SCALEY,
    ALPHA,
    ROTATION
    

## Springy For RecyclerView.
creat initialize SpringyAdapterAnimator in you Adapter Constructor.
    
           // pass recyclerView in it.
          springyAdapterAnimator = new SpringyAdapterAnimator(recyclerView);
           // set SpringyAdapterAnimationType
          springyAdapterAnimator.setSpringAnimationType(SpringyAdapterAnimationType.SLIDE_FROM_BOTTOM);
           // (optional) add Spring Config
          springyAdapterAnimator.addConfig(85,15);    
          
          // call this method in  onCreateViewHolder 
                  springyAdapterAnimator.onSpringItemCreate(itemView);
                  
                  
           // call this method in  onCreateViewHolder 
                 springyAdapterAnimator.onSpringItemBind(holder.itemView, position);



SpringyAdapterAnimation Types you can use.

    SLIDE_FROM_BOTTOM,
    SLIDE_FROM_RIGHT,
    SLIDE_FROM_LEFT,
    SCALE
    
![recylcer view](https://user-images.githubusercontent.com/11782272/27817252-c3d078b0-60ad-11e7-9cab-8a2ff4fe80c6.gif)



## About Facebook Rebound

<a href="http://facebook.github.io/rebound">Rebound</a> is a java library that
models spring dynamics. Rebound spring models can be used to create animations
that feel natural by introducing real world physics to your application.

Rebound is not a general purpose physics library; however, spring dynamics
can be used to drive a wide variety of animations. The simplicity of Rebound
makes it easy to integrate and use as a building block for creating more
complex components like pagers, toggles, and scrollers.

For examples and usage instructions head over to:

[facebook.github.io/rebound](http://facebook.github.io/rebound)
