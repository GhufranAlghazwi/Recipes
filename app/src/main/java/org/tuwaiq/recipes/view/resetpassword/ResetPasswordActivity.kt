package org.tuwaiq.recipes.view.resetpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import org.tuwaiq.recipes.databinding.ActivityResetPasswordBinding
import org.tuwaiq.recipes.view.login.LoginActivity

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityResetPasswordBinding
    val vm: ResetPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)

        var email = binding.resetPasswordEditText

        binding.resetButton.setOnClickListener {
            vm.resetPass(email.text.toString()).observe(this,{
                if (it){
                    Toast.makeText(this, "Email has been sent to you", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }

                else
                    Toast.makeText(this, "Failed to send reset email", Toast.LENGTH_LONG).show()
            })
        }

        setContentView(binding.root)
    }
}