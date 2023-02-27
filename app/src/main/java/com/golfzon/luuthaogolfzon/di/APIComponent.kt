package com.golfzon.luuthaogolfzon.di

import com.golfzon.luuthaogolfzon.model.PexelsService
import dagger.Component

@Component(modules = [APIModule::class])
interface APIComponent {
    fun inject(service: PexelsService)
}