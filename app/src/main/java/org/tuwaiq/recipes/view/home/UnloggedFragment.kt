package org.tuwaiq.recipes.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.view.login.LoginActivity
import org.tuwaiq.recipes.view.register.RegisterActivity

class UnloggedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_unlogged, container, false)

        var signUpButton = v.findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
                startActivity(Intent(context, RegisterActivity::class.java))
            }

        var loginHereTV = v.findViewById<TextView>(R.id.loginTV)
        loginHereTV.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
        return v

    }

}