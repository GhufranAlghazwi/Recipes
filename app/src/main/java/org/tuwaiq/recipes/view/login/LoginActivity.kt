package org.tuwaiq.recipes.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityLoginBinding
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.mainscreen.HomeActivity
import org.tuwaiq.recipes.view.home.profile.likes.LikesViewModel
import org.tuwaiq.recipes.view.register.RegisterActivity
import org.tuwaiq.recipes.view.resetpassword.ResetPasswordActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val vm: LoginViewModel by viewModels()
        val vm2: LikesViewModel by viewModels()

        var email = binding.loginEmailEditText
        var password = binding.loginPasswordEditText

        binding.loginButton.setOnClickListener {
            if (email.text!!.isEmpty() || password.text!!.isEmpty()) {
                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show()
            } else {
                vm.login(email.text.toString(), password.text.toString())
                    .observe(this, {
                        if(it.isNotEmpty()){
                            vm.getUserByFBID(it).observe(this, {
                                var id = it[0].id
                                SharedPreferenceHelper.saveUserID(this, id)
                                startActivity(Intent(this, HomeActivity::class.java))
                                Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_LONG).show()
                                ////
                                var uid = SharedPreferenceHelper.getUserID(this)
                                vm2.getLidByUid(uid, uid).observe(this, {
                                    if (it.size == 0){
                                        SharedPreferenceHelper.saveLikesID(this, "null")
                                    }
                                    else{
                                        var lid = it[0].id
                                        SharedPreferenceHelper.saveLikesID(this ,lid)
                                    }
                                })
                            })


                        }
                        else{
                            Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
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
