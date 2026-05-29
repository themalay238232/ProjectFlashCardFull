package com.example.projectflashcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UngDungLearnFlash()
        }
        Log.d("LearnFlashLifecycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LearnFlashLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LearnFlashLifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LearnFlashLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LearnFlashLifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LearnFlashLifecycle", "onDestroy")
    }
}
