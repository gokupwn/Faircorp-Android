package com.faircorp

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BasicActivity : AppCompatActivity(){
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_windows -> startActivity(
                Intent(this, WindowsActivity::class.java)
            )
            R.id.menu_website -> startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://0xh41.github.io"))
            )
            R.id.menu_email -> startActivity(
                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:hassanalachek@hotmail.com"))
            )
            R.id.act_refresh -> refresh()

        }
        return super.onContextItemSelected(item)
    }

    private fun refresh() {
        Toast.makeText(this, "Refresh ...", Toast.LENGTH_SHORT).show()
        finish()
        /* Disable blink effect after refresh */
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}