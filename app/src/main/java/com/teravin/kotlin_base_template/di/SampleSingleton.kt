package com.teravin.kotlin_base_template.di

import android.content.Context
import androidx.room.Room
import com.teravin.kotlin_base_template.R
import com.teravin.kotlin_base_template.contract.FlavorContract
import com.teravin.kotlin_base_template.contract.HttpContract
import com.teravin.kotlin_base_template.database.Database
import com.teravin.kotlin_base_template.util.TeravinFlow
import com.teravin.support.BuildConfig
import com.teravin.support.SecuredPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class SampleSingleton {

    private fun getUnsafeOkHttpClient(): OkHttpClient {

        val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256
            )
            .build()

        val specs: MutableList<ConnectionSpec> = ArrayList()
        specs.add(spec)
        specs.add(ConnectionSpec.COMPATIBLE_TLS)
        specs.add(ConnectionSpec.CLEARTEXT)

        val trustAllCerts = arrayOf<TrustManager>(
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
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val builder = OkHttpClient.Builder()
        builder.retryOnConnectionFailure(true)
        builder.connectionSpecs(specs)
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.connectTimeout(HttpContract.DEFAULT_CONNECT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        builder.readTimeout(HttpContract.DEFAULT_READ_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        builder.writeTimeout(HttpContract.DEFAULT_WRITE_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        builder.hostnameVerifier { _, _ -> true }
        builder.addInterceptor(logging)
        return builder.build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(FlavorContract.BASE_URL)
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun providesIoDispatchers() = Dispatchers.IO

    @Singleton
    @Provides
    internal fun provideSecuredPreference(@ApplicationContext context: Context): SecuredPreference =
        SecuredPreference(context, context.getString(R.string.app_name))

    @Singleton
    @Provides
    internal fun provideTeravinFlow(ioDispatcher: CoroutineDispatcher): TeravinFlow =
        TeravinFlow(ioDispatcher)

    @Singleton
    @Provides
    internal fun provideDatabase(@ApplicationContext context: Context): Database {
        val builder = Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DB_NAME
        ).fallbackToDestructiveMigration()

        val factory = SupportFactory(SQLiteDatabase.getBytes("S@mpl3!~".toCharArray()))
        builder.openHelperFactory(factory)
        return builder.build()
    }

}