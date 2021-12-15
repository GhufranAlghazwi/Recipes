package org.tuwaiq.recipes.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityHomeBinding
import org.tuwaiq.recipes.view.home.addRecipe.AddRecipeActivity
import org.tuwaiq.recipes.view.home.profile.ProfileFragment
import org.tuwaiq.recipes.view.home.profile.UnloggedFragment
import org.tuwaiq.recipes.view.home.recipes.RecipesFragment
import org.tuwaiq.recipes.view.home.search.SearchFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val vm: HomeViewModel by viewModels()
    var auth = Firebase.auth
    val currentUser = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrameLayout, RecipesFragment())
            .commit()

        binding.bNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ProfileFragment -> {
                    vm.checkLogin(currentUser).observe(this, {
                        if (it) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.mFrameLayout, ProfileFragment())
                                .commit()
                        } else {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.mFrameLayout, UnloggedFragment())
                                .commit()
                        }
                    })
                    true
                }
                R.id.recipes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mFrameLayout, RecipesFragment())
                        .commit()
                    true
                }
                R.id.addRecipe -> {
                    vm.checkLogin(currentUser).observe(this, {
                        if (it) {
                            startActivity(Intent(this, AddRecipeActivity::class.java))
                        }
                        else{
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.mFrameLayout, UnloggedFragment())
                                .commit()
                        }
                    })
                    true
                }
//                R.id.fridge -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.mFrameLayout, FridgeFragment())
//                        .commit()
//                    true
//                }
                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mFrameLayout, SearchFragment())
                        .commit()
                    true
                }
                else -> true


            }
        }


        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
    }
}