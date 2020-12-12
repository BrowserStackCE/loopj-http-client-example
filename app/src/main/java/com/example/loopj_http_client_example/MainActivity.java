package com.example.loopj_http_client_example;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cz.msebera.android.httpclient.Header;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.loopj.android.http.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView textViewIPObj = findViewById(R.id.textViewIp);
        final CheckBox cbUseProxy = findViewById(R.id.checkBoxUseProxy);

        cbUseProxy.setChecked(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final String proxyHost = System.getProperty("http.proxyHost");
        final String proxyPort = System.getProperty("http.proxyPort");

        getIPDetails(false, textViewIPObj, proxyHost, proxyPort);
        cbUseProxy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                   getIPDetails(isChecked, textViewIPObj, proxyHost, proxyPort);
                                               }
                                           }
        );



    }

    private void getIPDetails(boolean cbUseProxyIsChecked, final TextView textViewIPObj, String proxyHost, String proxyPort) {

        AsyncHttpClient client = new AsyncHttpClient();

        if(cbUseProxyIsChecked) {
            if(proxyHost!=null && proxyPort!=null) {
                System.out.println("Set proxy params: " + proxyHost + ":" +proxyPort);
                client.setProxy(proxyHost, Integer.parseInt(proxyPort));
            } else {
                System.out.println("System proxy configuration properties are empty / null.");
            }
        }



        client.get("http://ip-api.com/json", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String resp =  "Success response: " + new String(response);
                System.out.println(resp);
                textViewIPObj.setText(resp);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                String errorResp =  "Error response:" + new String(errorResponse);
                System.out.println(errorResp);
                textViewIPObj.setText(errorResp);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                System.out.println("Retry attempt: " + retryNo);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
