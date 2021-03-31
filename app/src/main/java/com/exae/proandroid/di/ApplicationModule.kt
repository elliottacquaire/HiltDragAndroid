package com.exae.proandroid.di

import android.content.Context
import android.os.Build
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.exae.proandroid.BuildConfig
import com.exae.proandroid.animation.ApiServiceAnno
import com.exae.proandroid.animation.OkHttpAnnotation
import com.exae.proandroid.animation.RetrofitAnno
import com.exae.proandroid.animation.RetrofitAnnoOther
import com.exae.proandroid.api.NetwrokService
import com.exae.proandroid.bean.TestBean
import com.exae.proandroid.bean.TestData
import com.exae.proandroid.common.Constants.URL_SERVICE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module  //模块用于向 Hlit 添加绑定，换句话说，是告诉 Hlit 如何提供不同类型的实例。
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideInt(): Int {
        return 1
    }

    @Provides
    @Singleton //单例
    fun provideTestData() : TestData{
        return TestData(22,"Jack Tom Sans")
    }

    //同一个 TestData 实例只能返回一个  不注释后会报错，若提供多个实例，请看下面 Retrofit 方式
//    @Provides
//    fun provideTestData() : TestData{
//        return TestData(22,"Jack Tom Sans")
//    }

    //参数bean 必须在其它地方实例化，否则报错
    @Provides
    fun testBean(bean : TestData) : TestBean {
        if (bean.age > 20){
            return TestBean(bean.age,bean.name)
        }
        return TestBean(18,"Jack Tom Brother")
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    @OkHttpAnnotation
    fun provideOkHttpClient(@ApplicationContext context: Context,
                            httpLoggingInterceptor: HttpLoggingInterceptor
//                            apiRequestInterceptor: ApiRequestInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
//        builder.addInterceptor(apiRequestInterceptor)
        builder.addInterceptor(httpLoggingInterceptor)
        builder.addInterceptor(ChuckerInterceptor.Builder(context).build())
        return builder.build()
    }


    @Singleton
    @Provides
    @RetrofitAnno
    fun provideRetrofit(
        @OkHttpAnnotation httpClient: OkHttpClient
//        liveDataCallAdapterFactory: LiveDataCallAdapterFactory,
//        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(URL_SERVICE)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(liveDataCallAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    @RetrofitAnnoOther
    fun provideRetrofitService(
        @OkHttpAnnotation httpClient: OkHttpClient
//        liveDataCallAdapterFactory: LiveDataCallAdapterFactory,
//        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(URL_SERVICE)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(liveDataCallAdapterFactory)
            .build()
//            .create(NetwrokService::class)
    }

    @Singleton
    @Provides
    @ApiServiceAnno
    fun provideApiService(@RetrofitAnno retrofit : Retrofit) : NetwrokService {
        return retrofit.create(NetwrokService::class.java)
    }


}