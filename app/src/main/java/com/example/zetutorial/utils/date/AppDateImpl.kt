package com.example.zetutorial.utils.date

import java.util.Date
import javax.inject.Inject

class AppDateImpl @Inject constructor(): AppDate {
    override fun getDate(): Date {
        return Date()
    }
}