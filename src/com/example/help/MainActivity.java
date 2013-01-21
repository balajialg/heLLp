package com.example.help;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import java.io.*;
import java.util.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.location.LocationListener;
import android.location.LocationManager;
import android.net.http.*;

public class MainActivity extends Activity {

	InputStream is;
	private Spinner spinner;
	private Button btn_post,btn_pic;
	private EditText desc;
    private String issue,description;
    private double mLat,mLong;
    private ImageView imgView;
    private Bitmap photo;
    GPSTracker Gps=new GPSTracker(this) ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        spinner = (Spinner) findViewById(R.id.spinner_1);
    	btn_post = (Button) findViewById(R.id.button_post);
    	desc= (EditText) findViewById(R.id.editText_desc);
    	btn_pic= (Button) findViewById(R.id.button_pic);
    	imgView = (ImageView) findViewById(R.id.img_preview);

    	spinner.setOnItemSelectedListener(new OnItemSelectedListener()
    	{
    		

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				issue=String.valueOf(spinner.getSelectedItem());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    	});
    	
    	btn_post.setOnClickListener(new OnClickListener() {
     
   		@Override
   		public void onClick(View v) {
   			if(Gps.canGetLocation)
   			{
                 mLat=Gps.getLatitude() ;
                 mLong=Gps.getLongitude() ;
                 Toast.makeText(MainActivity.this,mLat + "N , " + mLong + "E", Toast.LENGTH_SHORT).show();
   			}
        			
   			description=desc.getText().toString();
   			Toast.makeText(MainActivity.this,"Data Sent : " + "\nCategory : "+ issue + "\nDescription :" + description + "\nLocation:" + mLat + "N , " + mLong + "E", Toast.LENGTH_SHORT).show();
   		}
   		
    	});
    	
        btn_pic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {	
                // TODO Auto-generated method stub
            	
            	Intent intent= new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent,1) ;
            }
        });
	
	}

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == 1 && resultCode == RESULT_OK) {  
            photo = (Bitmap) data.getExtras().get("data"); 
            imgView.setImageBitmap(photo);
        }  
    }
    
}