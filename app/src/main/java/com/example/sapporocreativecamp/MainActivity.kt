package com.example.sapporocreativecamp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    data class Item(val imageURL: String,
                    val jname: String,
                    val ename: String
                    )

    var items = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add(Item(getString(R.string.japan), "日本", "Japan"))
        items.add(Item(getString(R.string.china), "中国", "China"))
        items.add(Item(getString(R.string.usa), "アメリカ", "America"))
        items.add(Item(getString(R.string.france), "フランス", "France"))
        items.add(Item(getString(R.string.england), "イギリス", "England"))

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = MyAdapter(this, items)

        listView.setOnItemClickListener { parent, view, position, id ->
            val item = items[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("imageUrl", item.imageURL)
            intent.putExtra("jname", item.jname)
            intent.putExtra("ename", item.ename)
            startActivity(intent)
        }
    }

    class MyAdapter(val context: Context, val items: MutableList<Item>): BaseAdapter() {
        var holder: ViewHolder? = null
        private val inflater: LayoutInflater = (context as Activity).layoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view: View
            if (convertView == null) {
                view = inflater.inflate(R.layout.cell_main, null)
                holder = ViewHolder(
                    view.findViewById(R.id.imageView) as ImageView,
                    view.findViewById(R.id.textView1) as TextView,
                    view.findViewById(R.id.textView2) as TextView
                )
                view.tag = holder
            } else {
                view = convertView!!
                holder = view.tag as ViewHolder
            }

            val item = items[position]

            holder?.iv?.let { Glide.with(context).load(item.imageURL).into(it) }
            holder?.tv1?.text = item.jname
            holder?.tv2?.text = item.ename

            return view
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        class ViewHolder(val iv: ImageView, val tv1: TextView, val tv2: TextView) {
        }
    }
}