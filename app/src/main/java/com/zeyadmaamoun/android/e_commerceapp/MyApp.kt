package com.zeyadmaamoun.android.e_commerceapp

import android.app.Application
import com.zeyadmaamoun.android.e_commerceapp.fragments.home.HomeViewModel
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductApiImplementation
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import com.zeyadmaamoun.android.e_commerceapp.utils.ConnectivityObserver
import com.zeyadmaamoun.android.e_commerceapp.utils.NetworkConnectivityUtils
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single {
                HttpClient(CIO) {
                    expectSuccess = true
                    install(ContentNegotiation) {
                        json()
                    }
                    install(HttpTimeout)
                }
            }

            single<ProductsApiService> {
                ProductApiImplementation(get())
            }

            viewModel {
                HomeViewModel(get())
            }

            factory<ConnectivityObserver> {
                NetworkConnectivityUtils(get())
            }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModules))
        }
    }
}