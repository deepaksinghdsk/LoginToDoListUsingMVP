package com.example.loginusingmvp.viewImpls

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.loginusingmvp.R
import com.example.loginusingmvp.presenter.Presenter1Imp
import com.example.loginusingmvp.presenter.Presenters
import com.example.loginusingmvp.viewImpls.afterLoginViewAndItsAdapter.AfterLogin

class MainActivity : AppCompatActivity(), Presenters.Presenter1.View {

    companion object {
        lateinit var username: EditText
        lateinit var password: EditText
    }

    private lateinit var presenter: Presenters.Presenter1

    private lateinit var login: Button
    private lateinit var signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter1Imp(this, this)

        init()

        login.setOnClickListener {
            presenter.verify(username.text.toString(), password.text.toString())
        }

        signup.setOnClickListener {
            if (password.text.length > 6)
                presenter.insert(username.text.toString(), password.text.toString())
            else
                error(
                    "length must be greater than 6",
                    password
                )
        }
    }

    private fun init() {
        username = findViewById(
            R.id.userName
        )
        password = findViewById(
            R.id.passWord
        )
        login = findViewById(R.id.login)
        signup = findViewById(R.id.signup)
    }

    override fun onStart() {
        super.onStart()
        val file: SharedPreferences = this.getSharedPreferences("session", Context.MODE_PRIVATE)
        val username: String? = file.getString("username", null)
        if (username != null) {
            val intent = Intent(this, AfterLogin::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }

    override fun reset(user: Boolean, pass: Boolean) {
        if (user)
            username.text = Editable.Factory().newEditable("")
        else if (pass)
            password.text = Editable.Factory().newEditable("")
    }

    override fun navigateNextScreen() {
        runOnUiThread {
            val intent = Intent(this, AfterLogin::class.java)
            intent.putExtra("username", username.text.toString())
            startActivity(intent)
        }
    }

    override fun error(message: String, errorIn: EditText) {
        runOnUiThread {
            errorIn.error = message
        }
    }
}
