package com.example.drunkapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class EditProfileData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_data)

        var Name = findViewById(R.id.Nameofuser) as EditText
        var age = findViewById(R.id.ageofuser) as EditText
        var tru1= findViewById(R.id.trus1ofuser) as EditText
        var tru2 = findViewById(R.id.trus2ofuser) as EditText

        val data = applicationContext.getSharedPreferences("UserPref", 0)

        val NameString = data.getString("username","Default value")
        val ageString = data.getString("userage","Default value")
        val tru1String = data.getString("usertru1","Default value")
        val tru2String = data.getString("usertru2","Default value")


        Name.setText(NameString)
        age.setText(ageString)
        tru1.setText(tru1String)
        tru2.setText(tru2String)


    }


    fun Save(view: View?){

        var Name = findViewById(R.id.Nameofuser) as EditText
        var age = findViewById(R.id.ageofuser) as EditText
        var tru1= findViewById(R.id.trus1ofuser) as EditText
        var tru2 = findViewById(R.id.trus2ofuser) as EditText

        val namestring = Name.text
        val agestring = age.text
        val tru1string = tru1.text
        val tru2string = tru2.text


        val data = applicationContext.getSharedPreferences("UserPref", 0)






        val editor = data.edit()
        editor.putString("username", namestring.toString())
        editor.putString("userage", agestring.toString())
        editor.putString("usertru1", tru1string.toString())
        editor.putString("usertru2", tru2string.toString())
        editor.apply()



        val intent = Intent(this, UserSetUp::class.java)
        startActivity(intent)
        finish()
    }
}