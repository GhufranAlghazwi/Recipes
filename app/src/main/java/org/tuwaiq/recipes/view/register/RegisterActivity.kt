package org.tuwaiq.recipes.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityRegisterBinding
import org.tuwaiq.recipes.model.User
import org.tuwaiq.recipes.util.ValidatorHelper
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

        binding.registerNameEditText.addTextChangedListener {
            if (it!!.isEmpty())
                binding.registerNameEditText.error = "Required"
        }

        binding.registerEmailEditText.addTextChangedListener{
            if (it!!.isEmpty())
                binding.registerEmailEditText.error = "Required"
            else if(!ValidatorHelper.validateEmail(it.toString()))
                binding.registerEmailEditText.error = "Invalid Email format"
        }

        binding.registerPasswordEditText.addTextChangedListener {
            if (it!!.isEmpty())
                binding.registerPasswordEditText.error = "Required"
            else if (!ValidatorHelper.validatePassword(it.toString()))
                binding.registerPasswordEditText.error = "Must be more than 8 characters"

        }

        binding.RegisterButton.setOnClickListener {
            if (name.text!!.isEmpty() || email.text!!.isEmpty() || !ValidatorHelper.validateEmail(email.text.toString()) || ValidatorHelper.validatePassword(password.text.toString())) {
                Toast.makeText(this, getString(R.string.fill_all_register), Toast.LENGTH_LONG)
                    .show()
            } else {
                vm.register(name.text.toString(), email.text.toString(), password.text.toString())
                    .observe(this, {
                        if (it) {
                            var user = User(auth.currentUser!!.uid, name.text.toString(), "")
                            vm.addUser(user)
                                .observe(this, {
                                    if (it) {
                                        Toast.makeText(
                                            this,
                                            getString(R.string.register_success),
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                                    startActivity(Intent(this, LoginActivity::class.java))
                                })
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.register_failed),
                                Toast.LENGTH_LONG
                            ).show()
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