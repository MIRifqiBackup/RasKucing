package com.miftakhulilmanrifqi82.navigation.raskucing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*handler untuk menahan activity sementara*/
        val handler = Handler()
        handler.postDelayed({ /*mulai activity ke MainActivity setelah 5 detik*/
            startActivity(Intent(getApplicationContext(), MainActivity::class.java))
            finish()
        } /*durasi 5000ms*/, 600)
    }
}

// class SplashActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        // check if this activity has been opened before
//        if (isFirstTime()) {
//            // it's the first time this activity is opened
//            // do something
//
//            // schedule the task to run after the splash screen is shown
//            Handler(Looper.getMainLooper()).postDelayed({
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            }, 1000)
//        } else {
//            // it's not the first time this activity is opened
//            // go directly to MainActivity
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//        }
//    }
//
//    private fun isFirstTime(): Boolean {
//        val preferences = getPreferences(Context.MODE_PRIVATE)
//        val isFirstTime = preferences.getBoolean("is_first_time", true)
//        if (isFirstTime) {
//            preferences.edit().putBoolean("is_first_time", false).apply()
//        }
//        return isFirstTime
//    }
// }

// class SplashActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//        }, 1000)
//    }
// }

// @SuppressLint("CustomSplashScreen")
// class SplashActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//
//        Handler().postDelayed({
//            startActivity<MainActivity>()
//        }, 500)
//    }
// }
