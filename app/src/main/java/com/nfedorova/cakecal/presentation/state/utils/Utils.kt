package com.nfedorova.cakecal.presentation.state.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



fun makeAdapter(recyclerView: RecyclerView, context: Context) {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.addItemDecoration(
        DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
    )
}

fun visible(tv : TextView, ed : EditText){
    tv.visibility = View.VISIBLE
    ed.visibility = View.VISIBLE
}
fun invisible(tv: TextView, ed: EditText){
    tv.visibility = View.GONE
    ed.visibility = View.GONE
}

fun validate(string: String) : Int {
    val stringInt: Int = if (string.isEmpty()) 1
    else string.toInt()
    if (stringInt == 0) return 1
    return stringInt
}

