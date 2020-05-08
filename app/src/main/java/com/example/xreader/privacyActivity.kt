package com.example.xreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_privacy.*

class privacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        privacy.loadUrl("file:///android_asset/policy.html")

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
