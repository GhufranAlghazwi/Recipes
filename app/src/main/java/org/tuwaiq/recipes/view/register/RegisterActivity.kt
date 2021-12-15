package org.tuwaiq.recipes.view.register

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.databinding.ActivityRegisterBinding
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.HomeActivity
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
            if (name.text!!.isEmpty() || email.text!!.isEmpty() || password.text!!.isEmpty()){
                Toast.makeText(this, "Fill all fields to login", Toast.LENGTH_LONG).show()
            }
            else{
                vm.register(name.text.toString(), email.text.toString(), password.text.toString())
                    .observe(this, {
                        if (it){
                            startActivity(Intent(this, LoginActivity::class.java))
                            vm.addUser(auth.currentUser!!.uid, name.text.toString(), "1").observe(this, {
                                if (it){
                                    //SharedPreferenceHelper.saveUserID()
                                    Toast.makeText(this, "Register success", Toast.LENGTH_LONG).show()
                                }

                            })
                            print("Register success")
                        }
                        else{
                            Toast.makeText(this, "Failed to Register", Toast.LENGTH_LONG).show()
                            print("Register failed")
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