package com.lozasolutions.fredompop.common.injection.module

import android.app.Application
import android.content.Context
import com.lozasolutions.fredompop.data.local.AlertManager
import com.lozasolutions.fredompop.data.local.InfoManager
import com.lozasolutions.fredompop.data.local.PreferencesHelper
import com.lozasolutions.fredompop.data.local.SessionManager
import com.lozasolutions.fredompop.data.remote.FreedompopAPI
import com.lozasolutions.fredompop.data.remote.retrofit.FreedompopAPIRetrofit
import com.lozasolutions.fredompop.data.remote.retrofit.FreedompopAPIService
import com.lozasolutions.fredompop.data.remote.retrofit.FreedompopServiceFactory
import com.lozasolutions.fredompop.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
class ApplicationTestModule(private val mApplication: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    /*************
     * MOCKS
     */

    @Provides
    @Singleton
    internal fun provideFreedompopAPIService(infoManager: InfoManager): FreedompopAPIService {
        return FreedompopServiceFactory.makeFreedompopService(infoManager)
    }


    @Provides
    @Singleton
    internal fun providePreferencesHelper(@ApplicationContext context:Context): PreferencesHelper {
        return PreferencesHelper(context)
    }


    @Provides
    @Singleton
    internal fun provideSessionManager( preferencesHelper: PreferencesHelper): SessionManager {
        return preferencesHelper
    }

    @Provides
    @Singleton
    internal fun provideAlertManager( preferencesHelper: PreferencesHelper): AlertManager {
        return preferencesHelper
    }

    @Provides
    @Singleton
    internal fun provideInfoManager( preferencesHelper: PreferencesHelper): InfoManager {
        return preferencesHelper
    }

    @Provides
    @Singleton
    internal fun provideFreedomPopAPI(service : FreedompopAPIService, sessionManager: SessionManager): FreedompopAPI {
        return FreedompopAPIRetrofit(service,sessionManager)
    }



}
