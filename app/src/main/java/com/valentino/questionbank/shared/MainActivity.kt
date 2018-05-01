package com.valentino.questionbank.shared

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.valentino.questionbank.R
import com.valentino.questionbank.api.ApiService
import com.valentino.questionbank.instructor.search.BrowseQuestionsActivity
import com.valentino.questionbank.shared.courses.CoursesFragment
import com.valentino.questionbank.utilities.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        prefs(this).session
        mode = intent.getStringExtra(MODE_PARAM)

        loadClassesFragment()

        ApiService.getUser(prefs(this).session, prefs(this).user) {
            navName.text = it.name
            navEmail.text = it.email
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_classes -> {
                loadClassesFragment()
            }
            R.id.nav_profile -> {
                // TODO: Make profile page
            }
            R.id.nav_search -> {
                if (mode == MODE_INSTRUCTOR) {
                    val intent = Intent(this, BrowseQuestionsActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.nav_logout -> {
                ApiService.logoutUser {
                    prefs(this).session = ""
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadClassesFragment() {
        val classesFragment = CoursesFragment()
        val args = Bundle()
        args.putString(MODE_PARAM, mode)
        classesFragment.arguments = args
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, classesFragment)
                .commit()
    }
}
