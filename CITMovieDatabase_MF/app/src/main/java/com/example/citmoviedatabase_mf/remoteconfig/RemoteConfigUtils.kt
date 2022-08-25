package com.example.citmoviedatabase_mf.remoteconfig

import android.util.Log
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigUtils {

    private const val TAG = "RemoteConfigUtilsTAG"

    private const val MAIN_ACTIVITY_APP_NAME = "main_activity_app_name"
    private const val MAIN_ACTIVITY_APP_NAME_COLOR = "main_activity_app_name_color"

    private val DEFAULTS: HashMap<String, Any> = hashMapOf(
        MAIN_ACTIVITY_APP_NAME to "MovieDB",
        MAIN_ACTIVITY_APP_NAME_COLOR to "#0091FF"
    )

    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig() : FirebaseRemoteConfig {

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

        val configSettings: FirebaseRemoteConfigSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if(BuildConfig.DEBUG){
                1
            }else {
                30
            }
        }

        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULTS)
            fetchAndActivate().addOnCompleteListener {
                Log.d("ABC", it.toString())
            }
        }

        return remoteConfig
    }

    fun getMainActivityAppName(): String = remoteConfig.getString(MAIN_ACTIVITY_APP_NAME)
    fun getMainActivityAppNameColor(): String = remoteConfig.getString(MAIN_ACTIVITY_APP_NAME_COLOR)

}