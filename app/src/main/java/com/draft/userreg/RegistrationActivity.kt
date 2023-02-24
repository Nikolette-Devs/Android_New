package com.draft.userreg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page_main)

        val regBtn: Button = findViewById(R.id.btn_register)

        regBtn.setOnClickListener{
            val loginIntent = Intent(this, HomeActivity::class.java)
            startActivity(/* intent = */ loginIntent)
        }

    }
}
