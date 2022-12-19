package com.example.firebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG
import com.google.firebase.remoteconfig.RemoteConfigConstants
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            }
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Toast.makeText(this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fetch failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
        val txtHi: TextView = findViewById(R.id.txtHi)
        val imgPrincipal: ImageView = findViewById(R.id.imgPrincipal)
        val backgroundColor:ConstraintLayout = findViewById(R.id.backgroundColor)
        val message: TextView = findViewById(R.id.txt_Message)

        val theme: Switch = findViewById(R.id.switch_theme)
        theme.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                txtHi.setTextColor(resources.getColor(R.color.black))
                backgroundColor.setBackgroundColor(resources.getColor(R.color.white))
                message.setTextColor(resources.getColor(R.color.black))

            }else{
                txtHi.setTextColor(resources.getColor(R.color.white))
                backgroundColor.setBackgroundColor(resources.getColor(R.color.black))
                message.setTextColor(resources.getColor(R.color.white))

            }
        }

        val conteudo: String = Firebase.remoteConfig.getString("Conteudo")
        txtHi.text = conteudo

        val backGround: Boolean = Firebase.remoteConfig.getBoolean( "SHOW_BACKGROUND_IMAGE")
        if(backGround){
            imgPrincipal.setImageResource(R.mipmap.ic_banner_natal)

        }else{
            imgPrincipal.setImageResource(R.mipmap.ic_banner)
        }

        val location: String = Firebase.remoteConfig.getString("Localizacao")
        message.text = location

    }

}