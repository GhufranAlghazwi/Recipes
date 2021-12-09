package org.tuwaiq.recipes.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityLoginBinding
import org.tuwaiq.recipes.view.home.HomeActivity
import org.tuwaiq.recipes.view.register.RegisterActivity
import org.tuwaiq.recipes.view.resetpassword.ResetPasswordActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val vm: LoginViewModel by viewModels()

        var email = binding.loginEmailEditText
        var password = binding.loginPasswordEditText

        binding.loginButton.setOnClickListener {
            if (email.text!!.isEmpty() || password.text!!.isEmpty()){
                Toast.makeText(this, "Fill all fields to login", Toast.LENGTH_LONG).show()
            }
            else{
                vm.login(email.text.toString(), password.text.toString())
                    .observe(this, {
                        if (it){
                            startActivity(Intent(this, HomeActivity::class.java))
                            Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
                            print("Login success")
                        }
                        else{
                            Toast.makeText(this, "Failed to login", Toast.LENGTH_LONG).show()
                            print("Login failed")
                        }
                    })
            }
        }

        binding.registerNowTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.forgetPasswordTextView.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        setContentView(binding.root)
    }
}