package com.zach.salman.springylib;

/**
 * Created by Salman_Zach on 11/25/2016.
 */

public interface SpringyListener {

    /*
    * hits when Spring is Active
    * */
    void onSpringStart();

    /*
    * hits when Spring is inActive
    * */

    void onSpringStop();
}
