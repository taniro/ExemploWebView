package tads.eaj.ufrn.exemplowebview

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

class MainActivity : AppCompatActivity() {

    lateinit var webview:WebView
    lateinit var progressBar: ProgressBar
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progressBar)
        button = findViewById(R.id.button)


        webview.loadUrl("http://tads.eaj.ufrn.br")
        webview.webViewClient = MyWebClient()


        button.setOnClickListener {
            val url = "http://tads.eaj.ufrn.br"
            val builder = CustomTabsIntent.Builder()
            val i = Intent()
            val pi = PendingIntent.getActivity (this, 55, i, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setActionButton(ContextCompat.getDrawable(applicationContext, R.mipmap.ic_launcher)!!.toBitmap(90,90), "Home", pi)
            builder.setToolbarColor(ContextCompat.getColor(applicationContext,android.R.color.black))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

    }

    inner class MyWebClient : WebViewClient(){

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
            webview.visibility = View.INVISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.INVISIBLE
            webview.visibility = View.VISIBLE
        }
    }

}