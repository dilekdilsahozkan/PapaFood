package com.dilsahozkan.papafood.di

import android.content.Context
import androidx.room.Room
import com.dilsahozkan.papafood.BuildConfig
import com.dilsahozkan.papafood.common.PreferencesManager
import com.dilsahozkan.papafood.data.local.RecipeDB
import com.dilsahozkan.papafood.data.local.dao.FavoriteDao
import com.dilsahozkan.papafood.data.local.dao.NotificationDao
import com.dilsahozkan.papafood.data.local.dao.RecipeDao
import com.dilsahozkan.papafood.data.remote.api.Service
import com.dilsahozkan.papafood.data.repository.RecipeRepository
import com.dilsahozkan.papafood.data.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDI {
    @Provides
    fun provideBaseUrl() = "https://api.spoonacular.com"

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(Service::class.java)

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

    @Singleton
    @Provides
    fun provideRecipeRepository(
        service: Service,
        preferencesManager: PreferencesManager
    ): RecipeRepository = RecipeRepositoryImpl(service, preferencesManager)

    @Module
    @InstallIn(SingletonComponent::class)
    object DatabasesModule {

        @Provides
        @Singleton
        fun provideCharactersDao(database: RecipeDB): RecipeDao {
            return database.recipeDao()
        }

        @Provides
        @Singleton
        fun provideFavoriteDao(database: RecipeDB): FavoriteDao {
            return database.favoriteDao()
        }

        @Provides
        @Singleton
        fun provideNotificationDao(database: RecipeDB): NotificationDao {
            return database.notificationDao()
        }

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): RecipeDB {
            return Room.databaseBuilder(
                context.applicationContext,
                RecipeDB::class.java,
                "recipe.db"
            ).build()
        }
    }
}