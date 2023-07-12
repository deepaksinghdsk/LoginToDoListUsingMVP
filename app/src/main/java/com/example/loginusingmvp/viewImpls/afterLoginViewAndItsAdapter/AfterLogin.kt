package com.example.loginusingmvp.viewImpls.afterLoginViewAndItsAdapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.loginusingmvp.R
import com.example.loginusingmvp.data.Data
import com.example.loginusingmvp.presenter.Presenter2Imp
import com.example.loginusingmvp.presenter.Presenters

class AfterLogin : AppCompatActivity(), Presenters.Presenter2.View {

    val presenter: Presenters.Presenter2 = Presenter2Imp(this, this)
    private lateinit var adapter: ListAdapter

    lateinit var file: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    lateinit var data: EditText
    private lateinit var list: ListView
    private lateinit var button: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        init()

        file.getString("username", "")?.let {
            presenter.getDataFromTable(it)
        }

        button.setOnClickListener {
            presenter.insert(data.text.toString(), intent.getStringExtra("username"))
        }
    }

    private fun init() {
        file = getSharedPreferences("session", Context.MODE_PRIVATE)
        data = findViewById(R.id.data)
        list = findViewById(R.id.list)
        button = findViewById(R.id.insert)

        editor = file.edit()
        editor.putString("username", intent.getStringExtra("username"))
        editor.apply()
    }

    override fun onBackPressed() {
        val i = Intent(Intent.ACTION_MAIN)
        i.addCategory(Intent.CATEGORY_HOME)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            editor.putString("username", null)
            editor.apply()
            finish()
        }
        return true
    }

    override fun reset() {
        data.text = Editable.Factory().newEditable("")
    }

    override fun setAdapter(data: MutableList<Data>) {
        adapter =
            ListAdapter(
                this, this,
                data
            )
        runOnUiThread {
            list.adapter = adapter
        }
    }

    override fun addInAdapter(data: Data) {
        runOnUiThread {
            adapter.insert(data)
        }
    }

    override fun removeFromAdapter(data: Data) {
        runOnUiThread {
            adapter.remove(data)
        }
    }
}