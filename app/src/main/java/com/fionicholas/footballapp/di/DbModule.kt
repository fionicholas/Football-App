package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.AppDatabase
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.getAppDatabase(get()) }

}