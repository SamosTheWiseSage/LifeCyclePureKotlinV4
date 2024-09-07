package se.gritacademy.getakelemwork.lifecyclepurekotlinv4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    lateinit var ChangeProfile:Button
    lateinit var btnReg:Button
    lateinit var btnLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        btnReg = findViewById(R.id.gotToReg)
        btnReg.setOnClickListener({
            val showtime = Intent(
                this@MainActivity3,MainActivity::class.java
            )
            startActivity(showtime)
        })
        btnLogin = findViewById(R.id.goToLogout)
        btnLogin.setOnClickListener({

            val showtime = Intent(
                this@MainActivity3,MainActivity2::class.java
            )
            startActivity(showtime)
        })
        ChangeProfile = findViewById(R.id.changeDetails)
        var clicker = 0
        ChangeProfile.setOnClickListener({
            val showtime = Intent(
                this@MainActivity3,MainActivity4::class.java
            )
            startActivity(showtime)
        })
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}