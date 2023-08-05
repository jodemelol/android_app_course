package com.chivata.app1.syntax

fun main(){
    var age = 3
    var age2 = 5
    monthOfYear(22)
    getTrimester(2)
    getSemester(7)
}

fun monthOfYear(age:Int){
    when (age){
        1 -> println("Enero")
        2 -> println("Febrero")
        3 -> println("Marzo")
        4 -> println("Abril")
        5 -> println("Mayo")
        6 -> println("Junio")
        7 -> println("Julio")
        8 -> println("Agosto")
        9 -> println("Septiembre")
        10 -> println("Octubre")
        11 -> println("Noviembre")
        12 -> println("Diciembre")
        else -> println("Mes no existe")
    }
}

fun getTrimester(month:Int){
    when(month){
        1,2,3 -> println("First trimester")
        4,5,6 -> println("Second trimester")
        7,8,9 -> println("Third trimester")
        10,11,12 -> println("Forth trimester")
        else -> println("Trimester dont exist")
    }
}

fun getSemester(month:Int){
    when(month){
        in 1..6 -> println("First semester")
        in 7..12 -> println("Second semester")
        else -> println("Semester dont exist")
    }
}