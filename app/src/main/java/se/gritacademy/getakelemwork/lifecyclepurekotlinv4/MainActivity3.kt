package se.gritacademy.getakelemwork.lifecyclepurekotlinv4

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    lateinit var imageview:ImageView
    lateinit var btn:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var mp:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        btn = findViewById(R.id.button)
        btn.setOnClickListener({
            val i = Intent(
                this@MainActivity3,
                MainActivity2::class.java
            )
            startActivity(i)
        })
        btn2 = findViewById(R.id.button2)
        btn2.setOnClickListener({
            val i = Intent(
                this@MainActivity3,
                MainActivity::class.java
            )
            startActivity(i)
        })
        btn3 = findViewById(R.id.button3)
        imageview = findViewById(R.id.imageView)
        mp = MediaPlayer.create(this,R.raw.owl_house_king_made_with_voicemod)
        btn3.setOnClickListener({
            imageview.animate().rotation(imageview.rotation - 360).start()
            mp.start()
        })
        imageview.setOnClickListener({

        })
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}