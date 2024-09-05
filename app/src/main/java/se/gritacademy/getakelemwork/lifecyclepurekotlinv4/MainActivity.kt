package se.gritacademy.getakelemwork.lifecyclepurekotlinv4

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
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


class MainActivity : AppCompatActivity() {
    var db = Firebase.firestore
    lateinit var et:EditText
    lateinit var et2:EditText
    lateinit var etMail:EditText
    lateinit var ettele:EditText
    lateinit var btn:Button
    lateinit var btn2:Button
    lateinit var showtimebtn:Button
    private val PREFS_NAME: String = "preferences"
    private val PREF_UNAME: String = "Username"
    private val PREF_PASSWORD: String = "Password"
    private val PREF_EMAIL: String = "Email"
    private val PREF_PHONE: String = "Phone"

    private val DefaultUnameValue = ""
    private var UnameValue: String? = null

    private val DefaultPasswordValue = ""
    private var PasswordValue: String? = null
    private val DefaultEmailValue = ""
    private var EmailValue: String? = null
    private val DefaultPhoneValue = ""
    private var PhoneValue: String? = null
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
        EmailValue = etMail.getText().toString()
        PhoneValue = ettele.getText().toString()
        println("onPause save name: $UnameValue")
        println("onPause save password: $PasswordValue")
        editor.putString(PREF_UNAME, UnameValue)
        editor.putString(PREF_PASSWORD, PasswordValue)
        editor.putString(PREF_EMAIL, EmailValue)
        editor.putString(PREF_PHONE, PhoneValue)
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
        EmailValue = settings.getString(PREF_EMAIL, DefaultEmailValue)
        PhoneValue = settings.getString(PREF_PHONE, DefaultPhoneValue)
        et.setText(UnameValue)
        et2.setText(PasswordValue)
        etMail.setText(EmailValue)
        ettele.setText(PhoneValue)
        println("onResume load name: $UnameValue")
        println("onResume load password: $PasswordValue")
        println("onResume load email: $EmailValue")
        println("onResume load phone: $PhoneValue")
    }

   // var et2 = findViewById<EditText>(R.id.editTextTextPassword)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        et = findViewById<EditText>(R.id.Username)
       et2 = findViewById<EditText>(R.id.editTextTextPassword)
       etMail = findViewById<EditText>(R.id.editTextTextEmailAddress)
       ettele = findViewById<EditText>(R.id.editTextPhone)
       btn = findViewById<Button>(R.id.submitbtn)
       btn2 = findViewById<Button>(R.id.loginbtn)
       showtimebtn = findViewById(R.id.showtimebtn)
       btn.setOnClickListener({
           write()
       })
    btn2.setOnClickListener({
        val i = Intent(
            this@MainActivity,
            MainActivity2::class.java
        )
        i.putExtra("Key", et.text.toString())
        startActivity(i)
   })  //et.text.toString()
       showtimebtn.setOnClickListener({
           val showtime = Intent(
               this@MainActivity,MainActivity3::class.java
           )
           startActivity(showtime)
       })
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun write(){
        val user = hashMapOf(
            "username" to et.text.toString(),
            "password" to et2.text.toString(),
            "Email" to etMail.text.toString(),
            "Phone" to ettele.text.toString()
        )

        db.collection("users")
            .whereEqualTo( "Email",etMail.text.toString()) //et.text.toString()
            //.whereEqualTo( et2.text.toString(),true)
            .get()
            .addOnSuccessListener { documents ->
                run {

                    if (documents.size() == 0){
                        Log.i("alrik", "IT is unik")
                        // Add a new document with a generated ID
                        db.collection("users")
                            .add(user)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
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