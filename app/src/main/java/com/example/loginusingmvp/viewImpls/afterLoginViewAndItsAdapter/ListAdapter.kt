package com.example.loginusingmvp.viewImpls.afterLoginViewAndItsAdapter

import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.loginusingmvp.R
import com.example.loginusingmvp.data.Data

class ListAdapter(
    private val c: Context,
    private val activity: AfterLogin,
    data: MutableList<Data>
) :
    BaseAdapter() {

    private val list = data

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.item_view, parent, false)
        }

        val text: TextView? = view?.findViewById(R.id.text)
        val menu: ImageView? = view?.findViewById(R.id.menu)
        text?.text = list[position].getData()

        text?.setOnClickListener {
            Toast.makeText(c, "Got clicked", Toast.LENGTH_SHORT).show()
        }
        try {
            menu?.setOnClickListener {
                val popup = PopupMenu(c, menu)
                val inflater: MenuInflater = popup.menuInflater
                inflater.inflate(R.menu.popup_menu, popup.menu)

                popup.setOnMenuItemClickListener {
                    if (it.itemId == R.id.edit) {
                        Log.d("listAdapter", "editing task")
                        activity.data.text =
                            Editable.Factory.getInstance().newEditable(list[position].getData())
                        activity.presenter.delete(
                            list[position].getData(),
                            activity.file.getString("username", ""),
                            list[position].getId()!!
                        )
                    } else if (it.itemId == R.id.delete) {
                        Log.d("listAdapter", "Deleting task, id = ${list[position].getId()}")
                        activity.presenter.delete(
                            list[position].getData(),
                            activity.file.getString("username", ""),
                            list[position].getId()!!
                        )
                    }
                    return@setOnMenuItemClickListener true
                }

                popup.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return view!!
    }

    fun insert(data: Data) {
        list.add(0, data)
        notifyDataSetChanged()
    }

    fun remove(data: Data) {
        var index: Int = -1

        for (info: Data in list) {
            if (info.getId() == data.getId())
                index = list.indexOf(info)
        }

        if (index != -1) {
            list.removeAt(index)
            notifyDataSetChanged()
        }
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}