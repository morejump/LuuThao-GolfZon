package com.golfzon.luuthaogolfzon.di

import com.golfzon.luuthaogolfzon.model.PexelsService
import com.golfzon.luuthaogolfzon.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [APIModule::class])
interface APIComponent {

    fun inject(service: PexelsService)

    fun inject(viewModel: ListViewModel)
}