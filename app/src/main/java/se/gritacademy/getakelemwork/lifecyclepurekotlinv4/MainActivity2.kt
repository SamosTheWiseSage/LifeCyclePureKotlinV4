package se.gritacademy.getakelemwork.lifecyclepurekotlinv4

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.math.log

class MainActivity2 : AppCompatActivity() {
    var db = Firebase.firestore
    private lateinit var et:EditText
    private lateinit var et2:EditText
    private lateinit var btn:Button
    private val PREFS_NAME: String = "preferences"
    private val PREF_UNAME: String = "Username"
    private val PREF_PASSWORD: String = "Password"

    private val DefaultUnameValue = ""
    private var UnameValue: String? = null

    private val DefaultPasswordValue = ""
    private var PasswordValue: String? = null
    override fun onStart() {
        super.onStart()

    }
    public override fun onPause() {
        super.onPause()
        savePreferences()
    }
    public override fun onResume() {
        super.onResume()
        loadPreferences()
    }
    private fun savePreferences() {
        val settings = getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        val editor = settings.edit()

        // Edit and commit
        UnameValue = et.getText().toString()
        PasswordValue = et2.getText().toString()
        println("onPause save name: $UnameValue")
        println("onPause save password: $PasswordValue")
        editor.putString(PREF_UNAME, UnameValue)
        editor.putString(PREF_PASSWORD, PasswordValue)
        editor.commit()
    }
    private fun loadPreferences() {
        val settings = getSharedPreferences(
            PREFS_NAME,
            MODE_PRIVATE
        )


        // Get value
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue)
        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue)
        et.setText(UnameValue)
        et2.setText(PasswordValue)
        println("onResume load name: $UnameValue")
        println("onResume load password: $PasswordValue")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        et = findViewById<EditText>(R.id.etuserlogin)
        et2 = findViewById<EditText>(R.id.etpasswordlogin)
        btn = findViewById(R.id.submitloginbtn)
        btn.setOnClickListener {
            login()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun login(){
        Log.i("alrik", "login: HEHREHREHHREHRHER")
        db.collection("users")
            .whereEqualTo( "Email","example@gmail.com") //et.text.toString()
            //.whereEqualTo( et2.text.toString(),true)
            .get()
            .addOnSuccessListener { documents ->
                run {

                    Log.i("alrik", "login: ")
                    for (document in documents) {
                        Log.d("alrik", "${document.id} => ${document.data["password"].toString()}")
                        if (document.data["password"].toString() == et2.text.toString())
                        Log.i("alrik", "login: MATCHING PASSWORD")
                        else Log.i("alrik", "login:  ${document.data["password"].toString()} "+et2.text.toString())
                    }

                }


            }
            .addOnFailureListener { exception ->
                Log.w("ERRORME", "Error getting documents: ", exception)
            }
    }
    fun read(){
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
    fun delete(){
        db.collection("cities").document("DC")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}