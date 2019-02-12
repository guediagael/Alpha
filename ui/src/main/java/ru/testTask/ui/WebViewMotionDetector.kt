package ru.testTask.ui

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class WebViewMotionDetector(
    private val swipeDetectorListener: SwipeDetectorListener,
    private val widthPixels: Float
) : GestureDetector.SimpleOnGestureListener() {

    private  val TAG = WebViewMotionDetector::class.java.simpleName
    // Minimal x and y axis swipe distance.
    private val MIN_SWIPE_DISTANCE_X = 100

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.i(TAG, "motion detected ${e1?.toString()} ${e2?.toString()}")
        val leftStartLimit = widthPixels*.15
        val rigthEndLimit = widthPixels - widthPixels*.15
        Log.i(TAG, "left flag $leftStartLimit  rightFlag$rigthEndLimit")


        if (e1 != null && e2 != null && (e1.x <=leftStartLimit || e1.x >= rigthEndLimit)) {
            // Get swipe delta value in x axis.
            val deltaX = e1.x - e2.x
            val deltaAbs = Math.abs(deltaX)
            Log.i(TAG,"delta: $deltaX")
            Log.i(TAG,"delta: $deltaAbs")
            if (deltaAbs > MIN_SWIPE_DISTANCE_X ) {
                if (deltaX < 0)swipeDetectorListener.swipeLeft()
                else swipeDetectorListener.swipeRight()
            }

        }
        return super.onFling(e1, e2, velocityX, velocityY)
    }

    interface SwipeDetectorListener {
        fun swipeLeft()
        fun swipeRight()
    }
}