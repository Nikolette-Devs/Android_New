package com.draft.userreg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sign_up_main.*

class SignUpActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_main)

        val userEmailSignUp:EditText = findViewById(R.id.email_input)
        val passwordInput:EditText = findViewById(R.id.signUp_password_input)
        val name:EditText = findViewById(R.id.name_input)
        val signUpBtn:Button = findViewById(R.id.btn_signUp_new)

        signUpBtn.setOnClickListener {
            val loginModel = LoginModel(name.text.toString(), passwordInput.text.toString(), userEmailSignUp.text.toString())
            val intent = Intent()
            intent.putExtra("name", name.text.toString())
            intent.putExtra("password", passwordInput.text.toString())
            intent.putExtra("email", userEmailSignUp.text.toString())

            intent.putExtra("LoginModel", loginModel)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}
