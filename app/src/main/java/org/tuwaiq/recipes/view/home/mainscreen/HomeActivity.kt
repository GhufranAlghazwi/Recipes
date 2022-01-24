package org.tuwaiq.recipes.view.home.mainscreen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityHomeBinding
import org.tuwaiq.recipes.util.LocalizationHelper
import org.tuwaiq.recipes.util.SharedPreferenceHelper
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
    lateinit var myMenu: Menu
    lateinit var mToolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)



        mToolbar = binding.mToolBar
        mToolbar.title = getString(R.string.app_name)
        setSupportActionBar(mToolbar)


        var fragmentToLoad = intent.getStringExtra("frgToLoad")
        if (fragmentToLoad == "ProfileFragment"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.mFrameLayout, ProfileFragment())
                .commit()
            myMenu.findItem(R.id.logout).setVisible(true)
            binding.bNavView.menu.findItem(R.id.ProfileFragment).setChecked(true)
        }
        else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.mFrameLayout, RecipesFragment())
                .commit()
        }

        binding.bNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ProfileFragment -> {
                    mToolbar.title = getString(R.string.profile)
                    vm.checkLogin(currentUser).observe(this, {
                        if (it) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.mFrameLayout, ProfileFragment())
                                .commit()
                            myMenu.findItem(R.id.logout).setVisible(true)
                        } else {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.mFrameLayout, UnloggedFragment())
                                .commit()
                            myMenu.findItem(R.id.logout).setVisible(false)
                        }
                    })
                    true
                }
                R.id.recipes -> {
                    mToolbar.title = getString(R.string.recipes)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mFrameLayout, RecipesFragment())
                        .commit()
                    myMenu.findItem(R.id.logout).setVisible(false)
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
                    myMenu.findItem(R.id.logout).setVisible(false)
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
                    myMenu.findItem(R.id.logout).setVisible(false)
                    true
                }
                else -> true


            }
        }


        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_toolbar, menu)
        myMenu = menu!!
        val item = menu!!.findItem(R.id.logout)
        if (item != null) {
            item.isVisible = false
        }
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                Firebase.auth.signOut()
                Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }

            R.id.localize -> {
                if(mToolbar.title=="Recipes"){
                    LocalizationHelper.changeLanguage(this,"ar")
                    SharedPreferenceHelper.saveLanguage(this,"ar")
                }

                else{
                    LocalizationHelper.changeLanguage(this,"en")
                    SharedPreferenceHelper.saveLanguage(this,"en")

                }
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
    }
}