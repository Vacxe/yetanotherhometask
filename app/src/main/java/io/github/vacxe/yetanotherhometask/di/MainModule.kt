package io.github.vacxe.yetanotherhometask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.vacxe.omdbapi.BuildConfig
import io.github.vacxe.omdbapi.api.OmdbApiFactory

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    fun omdbApi() = OmdbApiFactory.build(BuildConfig.omdbToken)
}
