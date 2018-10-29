package com.michaeljordan.desafioandroid.ui.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.michaeljordan.desafioandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fullscreen_image.*
import kotlinx.android.synthetic.main.transparent_toolbar.*


class FullScreenImageActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private var gestureDetectorCompat: GestureDetectorCompat? = null
    private var isFullScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)

        val imageUrl = intent.getStringExtra("image_url")
        val title = intent.getStringExtra("title")

        setToolbar(title)

        Picasso.with(baseContext)
                .load(imageUrl)
                .error(R.drawable.ic_no_poster)
                .into(iv_fullscreen_poster)

        gestureDetectorCompat = GestureDetectorCompat(this, this)
        showSystemUI()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetectorCompat!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar(movieTitle: String) {
        setSupportActionBar(toolbar_transparent)
        val actionBar = supportActionBar
        actionBar!!.title = movieTitle
        actionBar.setDisplayHomeAsUpEnabled(true)

        val window = this.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        hideToolbar()
        isFullScreen = false
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        showToolbar()
        isFullScreen = true
    }

    private fun hideToolbar() {
        toolbar_transparent.animate()
                .translationY(toolbar_transparent.height.toFloat() * -1)
                .alpha(0.0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        toolbar_transparent.visibility = View.GONE
                    }
                })
    }

    private fun showToolbar() {
        toolbar_transparent.animate()
                .translationY(0.0f)
                .alpha(1.0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        toolbar_transparent.visibility = View.VISIBLE
                    }
                })
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        if (isFullScreen) {
            hideSystemUI()
        } else {
            showSystemUI()
        }
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

}