package org.tuwaiq.recipes.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.databinding.ActivityRegisterBinding
import org.tuwaiq.recipes.model.User
import org.tuwaiq.recipes.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    val vm: RegisterViewModel by viewModels()
    var auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        var name = binding.registerNameEditText
        var email = binding.registerEmailEditText
        var password = binding.registerPasswordEditText

        binding.RegisterButton.setOnClickListener {
            if (name.text!!.isEmpty() || email.text!!.isEmpty() || password.text!!.isEmpty()) {
                Toast.makeText(this, "Fill all fields to login", Toast.LENGTH_LONG).show()
            } else {
                vm.register(name.text.toString(), email.text.toString(), password.text.toString())
                    .observe(this, {
                        if (it) {
                            var user = User(auth.currentUser!!.uid, name.text.toString(), "")
                            vm.addUser(user)
                                .observe(this, {
                                    if (it) {
                                        Toast.makeText(this, "Register success", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                    startActivity(Intent(this, LoginActivity::class.java))
                                })
                        } else {
                            Toast.makeText(this, "Failed to Register", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }

        binding.LoginHereTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        setContentView(binding.root)
    }
}