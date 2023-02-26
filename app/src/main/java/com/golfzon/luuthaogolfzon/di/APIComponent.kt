package com.golfzon.luuthaogolfzon.di

import com.golfzon.luuthaogolfzon.model.CountriesService
import dagger.Component

@Component(modules = [APIModule::class])
interface APIComponent {
    fun inject(service: CountriesService)
}