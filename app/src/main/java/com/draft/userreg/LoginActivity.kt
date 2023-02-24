package com.draft.userreg

import android.content.Intent
import android.content.IntentSender
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var userName :EditText
    private lateinit var passwordEdText :EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        userName = findViewById(R.id.name_input)
        passwordEdText = findViewById(R.id.password_input)
        val logBtn:Button = findViewById(R.id.btn_login)
        val suBtn:Button = findViewById(R.id.btn_sign_up)


        logBtn.setOnClickListener{
            val url = Uri.parse("http://google.com")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }

        suBtn.setOnClickListener{



            /*val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TIME, "This is the time")
            startActivity(Intent.createChooser(intent, "CST"))*/


            /* val number = "7573196576"
             val call = Intent(Intent.ACTION_CALL, Uri.parse("tel: $number"))
             startActivity(call)*/

            val loginIntent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(loginIntent)


        }
    }

    private var resultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            val data:Intent = result.data!!
            data.getStringExtra(("name"))!!
            val password= data.getStringExtra(("password"))!!
            val email= data.getStringExtra(("email"))!!

            val loginModel = data.getSerializableExtra("LoginModel") as LoginModel

            userName.setText(loginModel.username)
            passwordEdText.setText(loginModel.password)
        }
    }
}