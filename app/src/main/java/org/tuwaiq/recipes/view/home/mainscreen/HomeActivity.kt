package org.tuwaiq.recipes.view.home.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityHomeBinding
import org.tuwaiq.recipes.view.home.addRecipe.AddRecipeActivity
import org.tuwaiq.recipes.view.home.profile.userprofile.ProfileFragment
import org.tuwaiq.recipes.view.home.profile.userprofile.UnloggedFragment
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

        var mToolbar = binding.mToolBar
        mToolbar.title = getString(R.string.app_name)
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationOnClickListener {
            finish()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrameLayout, RecipesFragment())
            .commit()

        binding.bNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ProfileFragment -> {
                    mToolbar.title = getString(R.string.profile)
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
                    mToolbar.title = getString(R.string.recipes)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mFrameLayout, RecipesFragment())
                        .commit()
                    true
                }
                R.id.addRecipe -> {
                    mToolbar.title = getString(R.string.add_recipe)
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
                    mToolbar.title = getString(R.string.search)
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