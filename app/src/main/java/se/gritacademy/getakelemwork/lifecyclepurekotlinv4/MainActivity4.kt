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

class MainActivity4 : AppCompatActivity() {
    var db = Firebase.firestore
    lateinit var btn: Button
    lateinit var btn2:Button
    lateinit var et:EditText
    lateinit var et2:EditText
    lateinit var etMail:EditText
    lateinit var etPhone:EditText
    lateinit var etPostal:EditText
    lateinit var etDate:EditText
    private val PREFS_NAME: String = "preferences"
    private val PREF_UNAME: String = "Username"
    private val PREF_PASSWORD: String = "Password"
    private val PREF_EMAIL: String = "Email"
    private val PREF_PHONE: String = "Phone"
    private val PREF_Postal: String = "Postal"
    private val PREF_DATE: String = "Date"

    private val DefaultUnameValue = ""
    private var UnameValue: String? = null

    private val DefaultPasswordValue = ""
    private var PasswordValue: String? = null
    private val DefaultEmailValue = ""
    private var EmailValue: String? = null
    private val DefaultPhoneValue = ""
    private var PhoneValue: String? = null
    private var DefaultPostalValue = ""
    private var PostalValue: String? = null
    private var DefaultDateValue = ""
    private var DateValue: String? = null
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
        PhoneValue = etPhone.getText().toString()
        PostalValue = etPostal.getText().toString()
        DateValue = etDate.getText().toString()
        println("onPause save name: $UnameValue")
        println("onPause save password: $PasswordValue")
        editor.putString(PREF_UNAME, UnameValue)
        editor.putString(PREF_PASSWORD, PasswordValue)
        editor.putString(PREF_EMAIL, EmailValue)
        editor.putString(PREF_PHONE, PhoneValue)
        editor.putString(PREF_Postal, PostalValue)
        editor.putString(PREF_DATE, DateValue)
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
        PostalValue = settings.getString(PREF_Postal, DefaultPostalValue)
        DateValue = settings.getString(PREF_DATE, DefaultDateValue)
        et.setText(UnameValue)
        et2.setText(PasswordValue)
        etMail.setText(EmailValue)
        etPhone.setText(PhoneValue)
        etPostal.setText(PostalValue)
        etDate.setText(DateValue)
        println("onResume load name: $UnameValue")
        println("onResume load password: $PasswordValue")
        println("onResume load email: $EmailValue")
        println("onResume load phone: $PhoneValue")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        et = findViewById<EditText>(R.id.editTextText)
        et2 = findViewById<EditText>(R.id.editTextTextPassword2)
        etMail = findViewById<EditText>(R.id.editTextTextEmailAddress3)
        etPhone = findViewById<EditText>(R.id.editTextPhone2)
        etPostal = findViewById<EditText>(R.id.editTextTextPostalAddress2)
        etDate = findViewById<EditText>(R.id.editTextDate2)
        btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener({
        Update()
        })
        btn2 = findViewById<Button>(R.id.button3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun Update(){
        val updateUser = db.collection("users").document(MainActivity.docmentGlobalid)
        updateUser.update("Username",et.text.toString(), "password",et2.text.toString(),
            "Email",etMail.text.toString(),"Phone",etPhone.text.toString(),
            "Postal",etPostal.text.toString(),"Date",etDate.text.toString())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }
}