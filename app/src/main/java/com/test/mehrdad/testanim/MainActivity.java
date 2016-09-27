package com.test.mehrdad.testanim;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.mehrdad.testanim.chart.ChartFragment;
import com.test.mehrdad.testanim.menutest.Main2Activity;
import com.test.mehrdad.testanim.rsa.RSA;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button =(Button) findViewById(R.id.buttonshowChart);


        RSA rsa =  new RSA();
        try {
            String strEncrypt = rsa.Encrypt("test");
            Log.d("RSA" , "Encrypt = " + strEncrypt);
            String strDecrypt = rsa.Decrypt(strEncrypt);
            Log.d("RSA" , "Decrypt = " + strDecrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedInstanceState == null) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new ChartFragment())
                            .commit();
                }
            }
        });

        Button button2 =(Button) findViewById(R.id.buttonMp);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(MainActivity.this,MpChartActivity.class);

//                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);


                // Start Activity
                startActivity(intent);

            }
        });


    }







}