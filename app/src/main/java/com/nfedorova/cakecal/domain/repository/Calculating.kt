package com.nfedorova.cakecal.domain.repository

import android.content.Context

interface Calculating {
    fun getRatio(itemOne : String, itemTwo: String, dlOne: Int = 0, dlTwo : Int = 0,
                 wOne: Int = 0, wTwo: Int = 0): Double

    fun showTable(context: Context) : Boolean
}