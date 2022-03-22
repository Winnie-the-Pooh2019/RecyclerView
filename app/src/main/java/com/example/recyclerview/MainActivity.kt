package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = RecAdapter(ArrayList<ColorData>().apply { fillArray() })
    }

    private fun ArrayList<ColorData>.fillArray() {
        var i = 0
        while (true) {
            val nameId = resources.getIdentifier("in_color$i", "string", packageName)
            val colorId = resources.getIdentifier("in_color$i", "color", packageName)

            if (nameId == 0 || colorId == 0) break

            add(ColorData(resources.getString(nameId), resources.getColor(colorId, null)))
            i++
        }
    }
}

data class ColorData(val colorName: String, val colorHex: Int)

class Holder(view: View) : ViewHolder(view) {
    val colorName: TextView = view.findViewById(R.id.color_name)
    val colorRep: View = view.findViewById(R.id.color_repr)
}

class RecAdapter(private val colorsList: List<ColorData>) : Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_view_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.colorName.text = colorsList[position].colorName
        holder.colorRep.setBackgroundColor(colorsList[position].colorHex)
    }

    override fun getItemCount(): Int = colorsList.size
}