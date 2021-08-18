package dev.mmoreno.brbad.xumak

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dev.mmoreno.brbad.xumak.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      R.id.action_about -> {
        showAboutDialog()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }

  private fun showAboutDialog() {
    AlertDialog.Builder(this)
      .setTitle(getString(R.string.app_name))
      .setMessage(getString(R.string.about_dialog_message, BuildConfig.VERSION_NAME))
      .show()
  }
}