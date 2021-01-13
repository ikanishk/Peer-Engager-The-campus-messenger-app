package com.example.messenger

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import java.util.concurrent.TimeUnit

const val PHONE_NUMBER="phoneNumber"
class OtpActivity : AppCompatActivity() {
    var phoneNumber: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        initViews()
        showTimer(6000)  //this function when called displays the countdown timer
    }

    private fun showSignupActivity() {              //replace this with firebase AUth after submission
        startActivity(Intent(this,SignUpActivity::class.java))
        finish()
    }


    private fun showTimer(millisecinFuture: Long) {             //the ontick() and onFinish() functions handle how long and when are the  buttons gonna be visible or not
        val counterTv = findViewById<TextView>(R.id.counterTv)
        val resendBtn = findViewById<MaterialButton>(R.id.resendBtn)
        resendBtn.isEnabled = false
        object : CountDownTimer(millisecinFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                counterTv.isVisible = true
                counterTv.text = getString(R.string.Seconds_remaining, millisUntilFinished / 1000)
            }

            override fun onFinish() {
                resendBtn.isEnabled = true
                counterTv.isVisible = false
                showSignupActivity()        //Jugaad(replace it immediately after submission)
            }
        }.start()

    }

    private fun initViews() {
        phoneNumber = intent.getStringExtra(PHONE_NUMBER)
        var verifyTv = findViewById<TextView>(R.id.verifyTv)
        var sentcodeEt = findViewById<EditText>(R.id.sentcodeEt)
        verifyTv.text = getString(R.string.verify_number, phoneNumber)
        setSpannableString()

    }

    private fun setSpannableString() {
        phoneNumber = intent.getStringExtra(PHONE_NUMBER)
        val span = SpannableString(getString(R.string.waiting_text, phoneNumber))
        val clickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                showLoginActivity()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ds.linkColor
            }
        }


        span.setSpan(clickableSpan, span.length - 13, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        var waitingTv = findViewById<TextView>(R.id.waitingTv)
        waitingTv.movementMethod = LinkMovementMethod.getInstance()//to handle the click
        waitingTv.text = span

    }
    private fun showLoginActivity() {
        startActivity(
                Intent(this, LoginActivity::class.java)        //fuction for the "wrong number?" button to take the user on the Login window with a new tak again
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )//these are flags so that the user have resubmit the login pae and edittext is empty when the user lands back on that page
    }

    override fun onBackPressed() {

    }


}