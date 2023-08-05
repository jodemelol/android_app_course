package com.chivata.app1.syntax

fun main(){
    inmList()
}

fun exArray(){
    val daysOfWeek = arrayOf("Lunes","Martes","Miercoles","Jueves","Viernes")
    for(days in daysOfWeek.indices){
        println(daysOfWeek[days])
    }

    for(days in daysOfWeek){
        println("dia $days")
    }
}

fun inmList(){
    val inmutable = listOf("Lunes","Martes","Miercoles","Jueves","Viernes")
    println(inmutable.size)
    println(inmutable.last())

    val exFilter = inmutable.filter { it.contains("a") }
    println(exFilter)

    inmutable.forEach { day -> println(day) }
}