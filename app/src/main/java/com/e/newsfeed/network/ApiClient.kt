package com.e.newsfeed.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.security.cert.CertificateException

class ApiClient {

    private val BASE_URL = "https://newsapi.org/v2/"
    private var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit!!
    }

    fun getUnsafeOkHttpClient(): OkHttpClient.Builder{
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

                    // Install the all-trusting trust manager
                    val sslContext = SSLContext.getInstance("SSL")
                    sslContext.init(null,trustAllCerts,SecureRandom())

                    // Create an ssl socket factory with our all-trusting manager
                    val sslSocketFactory = sslContext.socketFactory
                    val builder = OkHttpClient.Builder()
                    builder.sslSocketFactory(sslSocketFactory,trustAllCerts as X509TrustManager)
                    builder.hostnameVerifier { _, _ -> true }
            return builder
        }catch (e: Exception){
            throw RuntimeException(e)
        }
    }
}