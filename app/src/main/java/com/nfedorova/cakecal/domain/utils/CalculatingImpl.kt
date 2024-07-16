package com.nfedorova.cakecal.domain.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.domain.repository.Calculating

class CalculatingImpl : Calculating {

    override suspend fun getRatio(
        itemOne: String, itemTwo: String, dlOne: Int,
        dlTwo: Int, wOne: Int, wTwo: Int,
    ): Double {
        var ratio = 0.0

        when {
            itemOne == "Round" && itemTwo == "Round" -> ratio = roundToRound(dlOne, dlTwo)
            itemOne == "Round" && itemTwo == "Rectangular" -> ratio =
                roundToRectangular(dlOne, dlTwo, wTwo)

            itemOne == "Round" && itemTwo == "Square" -> ratio = roundToSquare(dlOne, dlTwo)
            itemOne == "Rectangular" && itemTwo == "Round" -> ratio =
                rectangularToRound(dlOne, wOne, dlTwo)

            itemOne == "Rectangular" && itemTwo == "Rectangular" -> ratio =
                rectangularToRectangular(dlOne, wOne, dlTwo, wTwo)

            itemOne == "Rectangular" && itemTwo == "Square" -> ratio =
                rectangularToSquare(dlOne, wOne, dlTwo)

            itemOne == "Square" && itemTwo == "Round" -> ratio = squareToRound(dlOne, dlTwo)
            itemOne == "Square" && itemTwo == "Rectangular" -> ratio =
                squareToRectangular(dlOne, dlTwo, wTwo)

            itemOne == "Square" && itemTwo == "Square" -> ratio = squareToSquare(dlOne, dlTwo)
        }

        return ratio
    }

    override suspend fun showTable(context: Context): Boolean {
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
        return true
    }
}
