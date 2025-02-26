package com.rajesh.ecommassignment.di

import com.rajesh.ecommassignment.data.remote.ProductApi
import com.rajesh.ecommassignment.data.repository.ProductRepositoryImpl
import com.rajesh.ecommassignment.domain.repository.ProductRepository
import com.rajesh.ecommassignment.domain.usecase.GetProductDetailUseCase
import com.rajesh.ecommassignment.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }


    @Provides
    @Singleton
    fun provideProductApi(client: HttpClient): ProductApi = ProductApi(client)

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImpl(productApi)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepository): GetProductsUseCase =
        GetProductsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetProductDetailUseCase(repository: ProductRepository): GetProductDetailUseCase =
        GetProductDetailUseCase(repository)
}