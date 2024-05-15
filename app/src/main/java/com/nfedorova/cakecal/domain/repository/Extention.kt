package com.nfedorova.cakecal.domain.repository

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.nfedorova.cakecal.R
import java.security.MessageDigest

import kotlin.math.PI
import kotlin.math.pow

fun sha256(input: String): String {
    val bytes = input.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.joinToString("") {
        "%02x".format(it)
    }
}

fun showTable(context: Context){
    val dialogView = LayoutInflater.from(context).inflate(R.layout.show_table_dialog, null)
    val imageView = dialogView.findViewById<ImageView>(R.id.iv_table)
    imageView.setImageResource(R.drawable.table)
    val dialogBuilder = AlertDialog.Builder(context)
        .setView(dialogView)
        .setPositiveButton("Закрыть") { dialog, _ ->
            dialog.dismiss()
        }
    val alertDialog = dialogBuilder.create()
    alertDialog.show()
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

fun recalculating(itemOne : String, itemTwo: String, dlOne: Int = 0, dlTwo : Int = 0,
                          wOne: Int = 0, wTwo: Int = 0): Double {
    var ratio = 0.0

    if (itemOne == "Round" && itemTwo == "Round"){
        ratio = roundToRound(dlOne, dlTwo)
    }
    if (itemOne == "Round" && itemTwo == "Rectangular"){
        ratio = roundToRectangular(dlOne, dlTwo, wTwo)
    }
    if (itemOne == "Round" && itemTwo == "Square"){
        ratio = roundToSquare(dlOne, dlTwo)
    }
    if (itemOne == "Rectangular" && itemTwo == "Round"){
        ratio = rectangularToRound(dlOne, wOne, dlTwo)
    }
    if (itemOne == "Rectangular" && itemTwo == "Rectangular"){
        ratio = rectangularToRectangular(dlOne, wOne, dlTwo, wTwo)
    }
    if (itemOne == "Rectangular" && itemTwo == "Square"){
        ratio = rectangularToSquare(dlOne, wOne, dlTwo)
    }
    if (itemOne == "Square" && itemTwo == "Round"){
        ratio = squareToRound(dlOne, dlTwo)
    }
    if (itemOne == "Square" && itemTwo == "Rectangular"){
        ratio = squareToRectangular(dlOne, dlTwo, wTwo)
    }
    if (itemOne == "Square" && itemTwo == "Square"){
        ratio = squareToSquare(dlOne, dlTwo)
    }

    return ratio
}

fun roundToRound(dOne: Int, dTwo: Int): Double {
    val countOne = dOne.toDouble().pow(2.0)
    val countTwo = dTwo.toDouble().pow(2.0)

    return countTwo / countOne
}

fun roundToRectangular(dOne: Int, lTwo: Int, wTwo: Int): Double{
    val countOne = dOne.toDouble() * PI
    val countTwo = (lTwo * wTwo).toDouble()

    return if (countOne > countTwo)
        countOne / countTwo
    else countTwo / countOne
}


fun rectangularToRound(lOne: Int, wOne: Int, dTwo: Int): Double{
    val countOne = (lOne * wOne).toDouble()
    val countTwo = dTwo.toDouble() * PI

    return if (countOne > countTwo)
        countOne / countTwo
    else countTwo / countOne
}


fun rectangularToRectangular(lOne: Int, wOne: Int, lTwo: Int, wTwo: Int): Double {
    val countOne = (lOne * wOne).toDouble()
    val countTwo = (lTwo * wTwo).toDouble()

    return countTwo / countOne
}

fun rectangularToSquare(lOne: Int, wOne: Int, lTwo: Int): Double{
    val countOne = (lOne * wOne).toDouble()
    val countTwo = lTwo.toDouble().pow(2.0)

    return countTwo / countOne
}

fun squareToRectangular(lOne: Int, lTwo: Int, wTwo: Int): Double{
    val countOne = lOne.toDouble().pow(2.0)
    val countTwo = (lTwo * wTwo).toDouble()

    return countTwo / countOne
}

fun roundToSquare(dOne: Int, lTwo: Int): Double{
    val countOne = dOne.toDouble() * PI
    val countTwo = lTwo.toDouble().pow(2.0)

    return if (countOne > countTwo)
        countOne / countTwo
    else countTwo / countOne

}

fun squareToRound(lOne: Int, dTwo: Int): Double{
    val countOne = lOne.toDouble().pow(2.0)
    val countTwo = dTwo.toDouble() * PI

    return if (countOne > countTwo)
        countOne / countTwo
    else countTwo / countOne
}

fun squareToSquare(lOne: Int, lTwo: Int): Double{
    val countOne = lOne.toDouble().pow(2.0)
    val countTwo = lTwo.toDouble().pow(2.0)

    return countTwo / countOne
}