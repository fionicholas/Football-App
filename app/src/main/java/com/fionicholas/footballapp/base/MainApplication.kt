package com.fionicholas.footballapp.base

import com.fionicholas.footballapp.di.dbModule
import com.fionicholas.footballapp.di.leagueModule
import com.fionicholas.footballapp.di.matchModule
import com.fionicholas.footballapp.di.movieModule
import org.koin.core.module.Module

class MainApplication : BaseApplication() {

    override fun getDefinedModules(): List<Module> {
        return listOf(
            dbModule,
            leagueModule,
            matchModule,
            movieModule
        )
    }
}