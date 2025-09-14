package au.vu.nit3213.history.di

import au.vu.nit3213.history.data.Repository
import au.vu.nit3213.history.data.RepositoryImpl
import au.vu.nit3213.history.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepository(api: ApiService): Repository = RepositoryImpl(api)
}
