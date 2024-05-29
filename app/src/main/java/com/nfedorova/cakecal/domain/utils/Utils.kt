package com.nfedorova.cakecal.domain.utils

import kotlin.math.PI
import kotlin.math.pow


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