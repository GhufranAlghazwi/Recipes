package org.tuwaiq.recipes.view.home.mainscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tapadoo.alerter.Alerter
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityHomeBinding
import org.tuwaiq.recipes.util.LocalizationHelper
import org.tuwaiq.recipes.util.NotificationHelper
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.addRecipe.AddRecipeActivity
import org.tuwaiq.recipes.view.home.addRecipe.UnloggedAddFragment
import org.tuwaiq.recipes.view.home.profile.userprofile.ProfileFragment
import org.tuwaiq.recipes.view.home.profile.userprofile.UnloggedFragment
import org.tuwaiq.recipes.view.home.recipes.RecipesFragment
import org.tuwaiq.recipes.view.home.search.SearchFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val vm: HomeViewModel by viewModels()
    var auth = Firebase.auth
    val currentUser = auth.currentUser
    lateinit var mToolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)



        mToolbar = binding.mToolBar
        mToolbar.title = getString(R.string.app_name)
        setSupportActionBar(mToolbar)

        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val title = intent?.getStringExtra("title")
                val body = intent?.getStringExtra("body")

                if (title != null && body != null) {
                    showNotificationDialog(title, body)
                }
            }
        }

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter("recipes"))


        var fragmentToLoad = intent.getStringExtra("frgToLoad")
        if (fragmentToLoad == "ProfileFragment"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.mFrameLayout, ProfileFragment())
                .commit()
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
                        } else {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.mFrameLayout, UnloggedFragment())
                                .commit()
                        }
                    })
                    //hideLocalizeIcon()
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
                                .replace(R.id.mFrameLayout, UnloggedAddFragment())
                                .commit()
                        }
                    })
                    //hideLocalizeIcon()
                    true
                }
                R.id.search -> {
                    mToolbar.title = getString(R.string.search)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mFrameLayout, SearchFragment())
                        .commit()
                    //hideLocalizeIcon()
                    true
                }
                else -> true


            }
        }


        setContentView(binding.root)
    }

    private fun showNotificationDialog(title: String, body: String) {
        val alertOnClickListener = View.OnClickListener {
            Alerter.hide()
            Toast.makeText(this, "Alert clicked", Toast.LENGTH_SHORT).show()
        }

        Alerter
            .create(this)
            .setTitle(title)
            .setText(body)
            .setBackgroundColorRes(R.color.baby_orange)
            .setTitleAppearance(R.style.AlertTextAppearance)
            .setTextAppearance(R.style.AlertTextAppearance)
            .setIcon(R.drawable.soup)
            .setDuration(7000)
            .setOnClickListener(alertOnClickListener)
            .enableSwipeToDismiss()
            .show()
    }


}