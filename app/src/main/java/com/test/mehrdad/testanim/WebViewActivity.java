package com.test.mehrdad.testanim;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        browser = (WebView) findViewById(R.id.webView1);

        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        browser.setWebViewClient(new MyBrowser());


        String summary="<HTML>" +
                "<HEAD>" +
                "<TITLE>Your Title Here</TITLE>" +
                "</HEAD>" +
                "<BODY BGCOLOR='FFFFFF'>" +
                "<CENTER><IMG SRC='clouds.jpg' ALIGN='BOTTOM'> </CENTER>" +
                "<HR>" +
                "<a href='http://somegreatsite.com'>Link Name</a>" +
                "is a link to another nifty site" +
                "<H1>This is a Header</H1>" +
                "<H2>This is a Medium Header</H2>" +
                "Send me mail at <a href='mailto:support@yourcompany.com'>" +
                "support@yourcompany.com</a>." +
                "<P> This is a new paragraph!" +
                "<P> <B>This is a new paragraph!</B>" +
                "<BR> <B><I>This is a new sentence without a paragraph break, in bold italics.</I></B>\n" +
                "<HR>" +
                "</BODY>" +
                "</HTML>";



     //   browser.loadUrl("https://www.google.com");

        browser.loadData(summary, "text/html", null);

        browser.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(WebViewActivity.this);
                    mProgress.show();
                }
                mProgress.setMessage("Loading " + String.valueOf(progress) + "%");
                if (progress == 100) {
                    mProgress.dismiss();
                    mProgress = null;
                }
            }
        });

    }


    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("WebViewClient", "shouldOverrideUrlLoading");
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d("WebViewClient", "onPageStarted");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("WebViewClient", "onPageFinished");
        }

    }


}
