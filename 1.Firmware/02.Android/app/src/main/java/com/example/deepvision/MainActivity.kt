package com.example.deepvision

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    //OpenCV库加载并初始化成功后的回调函数
    private val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            // TODO Auto-generated method stub
            when (status) {
                SUCCESS -> {
                    Log.i(TAG, "成功加载opencv")
                    val toast = Toast.makeText(
                        applicationContext,
                        "成功加载opencv！", Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
                else -> {
                    super.onManagerConnected(status)
                    Log.i(TAG, "加载失败")
                    val toast1 = Toast.makeText(
                        applicationContext,
                        "加载失败！", Toast.LENGTH_LONG
                    )
                    toast1.setGravity(Gravity.CENTER, 0, 0)
                    toast1.show()
                }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume() {
        super.onResume()
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback)
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!")
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
        }
    }
}