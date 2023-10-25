package com.example.zetutorial.utils

import java.util.Date
import javax.inject.Inject

interface AppDate {

    fun getDate(): Date
}

class AppDateImpl @Inject constructor(): AppDate {
    override fun getDate(): Date {
        return Date()
    }

}