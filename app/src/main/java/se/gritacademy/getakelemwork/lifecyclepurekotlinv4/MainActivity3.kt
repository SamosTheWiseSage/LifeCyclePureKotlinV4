package se.gritacademy.getakelemwork.lifecyclepurekotlinv4

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        btn = findViewById(R.id.button)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        imageview = findViewById(R.id.imageView)
        btn3.setOnClickListener({
            imageview.animate().rotation(imageview.rotation - 360).start()
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}