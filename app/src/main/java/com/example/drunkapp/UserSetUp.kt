package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class UserSetUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_set_up)

        var Name = findViewById(R.id.Nameofuser) as TextView
        var age = findViewById(R.id.ageofuser) as TextView
        var tru1= findViewById(R.id.trus1ofuser) as TextView
        var tru2 = findViewById(R.id.trus2ofuser) as TextView

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
    fun EditPRofile(view: View?){
        val intent = Intent(this, EditProfileData::class.java)
        startActivity(intent)
    }
}