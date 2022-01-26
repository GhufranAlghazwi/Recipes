package org.tuwaiq.recipes.view.home.profile.userprofile

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.mainscreen.HomeActivity
import org.tuwaiq.recipes.view.home.profile.ProfileVPAdapter

class ProfileFragment : Fragment() {
    var currentUser = Firebase.auth.currentUser
    val db = Firebase.firestore
    lateinit var username: String
//    val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_profile, container, false)

        var name = v.findViewById<TextView>(R.id.ProfileNameViewText)
        var shortCut = v.findViewById<TextView>(R.id.usernameShortCut)

        db.collection("users").document(currentUser!!.uid).addSnapshotListener() { result, e ->
            username = result?.getString("fullname")!!
            name.text = username
            val listName = username.split(" ")
            if (listName.size >= 2)
                shortCut.text = listName[0].first().toUpperCase() +""+ listName[1].first().toUpperCase()
            else if (listName.size == 1)
                shortCut.text = listName[0].first() +"".toUpperCase()

        }

        var viewPager2 = v.findViewById<ViewPager2>(R.id.profileViewPager)
        viewPager2.adapter = ProfileVPAdapter(this)


        var navBar = v.findViewById<BottomNavigationView>(R.id.bottomNavigationViewProfile)
        navBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myRecipes -> {
                    viewPager2.currentItem = 0
                    true
                }
                R.id.likes -> {
                    viewPager2.currentItem = 1
                    true
                }
                else -> false
            }
        }


        var myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> navBar.getMenu().findItem(R.id.myRecipes).setChecked(true);
                    1 -> navBar.getMenu().findItem(R.id.likes).setChecked(true);
                }
            }
        }
        viewPager2.registerOnPageChangeCallback(myPageChangeCallback)


        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_toolbar, menu)
        menu.findItem(R.id.localize).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                Firebase.auth.signOut()
                SharedPreferenceHelper.saveLikesID(requireContext(), "null")
                SharedPreferenceHelper.saveUserID(requireContext(), "null")
                Toast.makeText(this.context, "Logout successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this.context, HomeActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}