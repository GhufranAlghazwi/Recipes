package org.tuwaiq.recipes.view.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.view.home.HomeViewModel

class ProfileFragment : Fragment() {
    var currentUser = Firebase.auth.currentUser
    val db = Firebase.firestore
    lateinit var username: String
    val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_profile, container, false)

        var name = v.findViewById<TextView>(R.id.ProfileNameViewText)
        var shortCut = v.findViewById<TextView>(R.id.usernameShortCut)

        db.collection("users").document(currentUser!!.uid).addSnapshotListener() { result, e ->
            username = result?.getString("fullname")!!
            name.text = username
            val listName = username.split(" ")
            shortCut.text = listName[0].first() +"" +listName[1].first()
        }

        var logout = v.findViewById<ImageView>(R.id.logoutIcon)
        logout.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(context, "Logout successfully", Toast.LENGTH_SHORT).show()
            updateUI(currentUser)
        }
        return v
    }

    fun updateUI(user: FirebaseUser?){
        HomeViewModel().checkLogin(user).observe(viewLifecycleOwner, {
            if (!it)
                supportFragmentManager
                    ?.replace(R.id.mFrameLayout, UnloggedFragment())
                    ?.commit()
        })
    }


}