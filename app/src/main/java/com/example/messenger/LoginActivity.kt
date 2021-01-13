package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hbb20.CountryCodePicker

class LoginActivity : AppCompatActivity() {
    private lateinit var phoneNumber:String
    private lateinit var countryCode:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val phoneNumberEt=findViewById<EditText>(R.id.phoneNumberEt)
        val nextBtn=findViewById<MaterialButton>(R.id.nextBtn)
        val ccp=findViewById<CountryCodePicker>(R.id.ccp)

        phoneNumberEt.addTextChangedListener {
            nextBtn.isEnabled=!(it.isNullOrEmpty() || it.length>10)
            countryCode=ccp.selectedCountryCodeWithPlus
            phoneNumber= countryCode+phoneNumberEt.text.toString()
        }

        nextBtn.setOnClickListener {
        checknumber()
        }

    }

    private fun checknumber() {
        notifyUser()

    }

    private fun notifyUser() {
        MaterialAlertDialogBuilder(this).apply {
            setMessage("We will be verifying the phone number $phoneNumber ...is that okay with you?")
            setPositiveButton("OK"){_,_ ->
                showotpActivity()
            }
            setNegativeButton("Edit"){dialog,_ ->
            dialog.dismiss()
            }
            setCancelable(false)
            create()
            show()
        }
    }

    private fun showotpActivity() {
      startActivity(Intent(this,OtpActivity::class.java).putExtra(PHONE_NUMBER,phoneNumber))
        finish()
    }
}