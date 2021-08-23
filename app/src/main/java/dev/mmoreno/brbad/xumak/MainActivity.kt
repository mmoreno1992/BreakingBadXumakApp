package dev.mmoreno.brbad.xumak

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dev.mmoreno.brbad.xumak.databinding.ActivityMainBinding
import dev.mmoreno.brbad.xumak.networking.NetworkListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent


class MainActivity : AppCompatActivity(), KoinComponent {

  lateinit var binding: ActivityMainBinding
  private lateinit var networkListener: NetworkListener
  private val viewModel by viewModel<SharedViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setTheme(R.style.Theme_BreakingBadXumakApp)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    val navController = navHostFragment.navController

    setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))

    setListeners()
  }

  /**
   * Set the listeners (network and views)
   */
  private fun setListeners() {
    networkListener = NetworkListener()
    networkListener.checkNetworkAvailability(this@MainActivity)
      .observe(this@MainActivity, {
        showNetworkStatusView(it)
      })
  }

  /**
   * Styles and animates the view to show the
   * network status.
   * @param isOnline: network status
   *
   * If it is offline it always shows the view
   * If it is online but it is the first time the
   * user opens the app it doesn't show the view
   */
  private fun showNetworkStatusView(isOnline: Boolean) {
    if (!isOnline
      || (isOnline && !(viewModel.firstTimeNetworkStatusLoaded))
    ) {

      viewModel.changeNetworkStatus(isOnline)

      val backgroundColor = ContextCompat.getColor(
        this,
        if (isOnline) R.color.light_blue_600 else R.color.red_700
      )

      val textColor: Int = ContextCompat.getColor(
        this,
        if (isOnline) android.R.color.black else android.R.color.white
      )

      val text = if (isOnline) getString(R.string.network_status_online) else
        getString(R.string.network_status_offline)

      binding.networkStatus.apply {
        setBackgroundColor(backgroundColor)
        setTextColor(textColor)
        setText(text)
      }

      val animation = AnimationUtils.loadAnimation(
        this,
        R.anim.fade_in_out_animation
      )
      animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
          binding.networkStatus.visibility = View.VISIBLE
        }

        override fun onAnimationEnd(animation: Animation?) {
          binding.networkStatus.visibility = View.GONE
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
      })

      binding.networkStatus.startAnimation(
        animation
      )
    }

  }

  /**
   * Inflating the menu
   */
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  /**
   * Responding to click menu items
   * @param item: the item that was clicked
   */
  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      R.id.action_about -> {
        showAboutDialog()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }

  /**
   * Shows information about the app
   */
  private fun showAboutDialog() {
    AlertDialog.Builder(this)
      .setTitle(getString(R.string.app_name))
      .setMessage(getString(R.string.about_dialog_message, BuildConfig.VERSION_NAME))
      .show()
  }
}