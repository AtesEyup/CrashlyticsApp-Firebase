package com.eyp.crashlyticstestapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Uygulama analizinin raporlandığı zaman girilen bir userId varsa filtreleme yapılabilir.
        // userId aktif edilmediği zaman identity(1,1) artacak şekilde telefonlara id verilir.
        FirebaseCrashlytics.getInstance().setUserId("Eyp")

        // Eklediğimiz log yalnızca bir crash ve ANR olaylarında Logs menüsü altında gösterilir.
        // Herhangi bir olay olmadığı sürece log'lar ram'de tutulur. Yeni bir log eklendiği zaman önceki log silinir.
        FirebaseCrashlytics.getInstance().log("OnCreate Created")

        // Test Runtime Crash
//        throw RuntimeException("Test Crash")

        findViewById<Button>(R.id.btnCrash).setOnClickListener {
            FirebaseCrashlytics.getInstance().log("Crash Button Clicked")
            val value = "test"
            value.toInt()
        }

        findViewById<Button>(R.id.btnANR).setOnClickListener {
            FirebaseCrashlytics.getInstance().log("ANR Button Clicked")
            // Android'de Main Thread ( Ui ) yaklaşık 5 saniye kadar cevap vermezse ANR ( Android Not Responing ) hatası fırlatır
            // Main Thread 8 saniye kadar kilitleyip ANR hatası fırlatıyoruz
            Thread.sleep(8000)
        }
    }
}