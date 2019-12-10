package com.example.droidcafe

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "com.example.droidcafe.extra.MESSAGE"
    }

    private var mOrderMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            onClickCart(view)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    fun showDonutOrder(view: View) {
        mOrderMessage = getString(R.string.donut_order_message)
        displayToast()
    }

    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    fun showIceCreamOrder(view: View) {
        mOrderMessage = getString(R.string.ice_cream_order_message)
        displayToast()
    }

    /**
     * Shows a message that the froyo image was clicked.
     */
    fun showFroyoOrder(view: View) {
        mOrderMessage = getString(R.string.froyo_order_message)
        displayToast()
    }

    // Helpers

    private fun onClickCart(view: View) {
        mOrderMessage?.run {
            navigateToOrders()
        } ?: run {
            Snackbar.make(view, "Please select a dessert first!", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    private fun displayToast() {
        mOrderMessage?.let { message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            navigateToOrders()
        }
    }

    private fun navigateToOrders() {
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, mOrderMessage)
        startActivity(intent)
    }
}
