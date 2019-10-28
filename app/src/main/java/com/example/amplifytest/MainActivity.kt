package com.example.amplifytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amazonaws.mobile.client.AWSMobileClient
import android.widget.TextView
import com.amazonaws.mobile.client.UserStateDetails
import android.util.Log
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserState



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AWSMobileClient.getInstance()
            .initialize(applicationContext, object : Callback<UserStateDetails> {
                override fun onResult(userStateDetails: UserStateDetails) {
                    when (userStateDetails.userState) {
                        UserState.SIGNED_IN -> runOnUiThread {
                            val textView = findViewById(R.id.amplifyText) as TextView
                            textView.text = "Logged IN"
                        }
                        UserState.SIGNED_OUT -> runOnUiThread {
                            val textView = findViewById(R.id.amplifyText) as TextView
                            textView.text = "Logged OUT"
                        }
                        else -> AWSMobileClient.getInstance().signOut()
                    }
                }

                override fun onError(e: Exception) {
                    Log.e("INIT", e.toString())
                }
            })
    }
}
