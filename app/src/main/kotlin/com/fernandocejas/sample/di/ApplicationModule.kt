package com.fernandocejas.sample.di

import android.content.Context
import com.fernandocejas.sample.AndroidApplication
import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.executor.ThreadScheduler
import com.fernandocejas.sample.framework.network.Endpoints
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideExecutionScheduler(threadScheduler: ThreadScheduler): ExecutionScheduler = threadScheduler

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Endpoints.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
