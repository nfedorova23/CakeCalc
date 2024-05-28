package com.nfedorova.cakecal.domain.mapper

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.nfedorova.cakecal.R
import kotlin.math.PI
import kotlin.math.pow

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

fun recalculating(itemOne : String, itemTwo: String, dlOne: Int = 0, dlTwo : Int = 0,
                          wOne: Int = 0, wTwo: Int = 0): Double {
    var ratio = 0.0

    when {
        itemOne == "Round" && itemTwo == "Round" ->  ratio = roundToRound(dlOne, dlTwo)
        itemOne == "Round" && itemTwo == "Rectangular" -> ratio = roundToRectangular(dlOne, dlTwo, wTwo)
        itemOne == "Round" && itemTwo == "Square" -> ratio = roundToSquare(dlOne, dlTwo)
        itemOne == "Rectangular" && itemTwo == "Round" -> ratio = rectangularToRound(dlOne, wOne, dlTwo)
        itemOne == "Rectangular" && itemTwo == "Rectangular" -> ratio = rectangularToRectangular(dlOne, wOne, dlTwo, wTwo)
        itemOne == "Rectangular" && itemTwo == "Square" -> ratio = rectangularToSquare(dlOne, wOne, dlTwo)
        itemOne == "Square" && itemTwo == "Round" -> ratio = squareToRound(dlOne, dlTwo)
        itemOne == "Square" && itemTwo == "Rectangular" -> ratio = squareToRectangular(dlOne, dlTwo, wTwo)
        itemOne == "Square" && itemTwo == "Square" -> ratio = squareToSquare(dlOne, dlTwo)
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