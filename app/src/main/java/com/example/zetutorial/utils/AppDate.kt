package com.example.zetutorial.utils

import java.util.Date

interface AppDate {

    fun getDate(): Date
}

class AppDateImpl: AppDate {
    override fun getDate(): Date {
        return Date()
    }

}