package com.example.zetutorial.utils

interface Mapper<T, Z> {

    fun converter(from: T): Z
}
