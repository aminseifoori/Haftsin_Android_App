package com.ampesoftware.haftsin;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends Activity {
	ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	super.onCreate(savedInstanceState);
    	
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        setContentView(R.layout.activity_main);
        pb=(ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.VISIBLE);
		final dbhelper myDbHelper = new dbhelper(this);
		Thread tr=new Thread(){
        	@Override
        	public void run(){
        		try{
        	        try {
        	        	myDbHelper.createDataBase();
        	        } catch (IOException ioe) {
        	        throw new Error("Unable to create database");
        	        }
        	        myDbHelper.openDataBase();
        	        myDbHelper.close();
        		}catch (SQLException e) {
        			 e.getStackTrace();
				}finally{
					
					/*Intent in=new Intent(MainActivity.this,MainMenuActivity.class);
					startActivity(in);
					
					finish();*/
				}
        	}
        };
        tr.start();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
            	pb.setVisibility(View.GONE);
            	Intent mainIntent = new Intent(MainActivity.this,MainMenuActivity.class);
                MainActivity.this.startActivity(mainIntent);
                
                MainActivity.this.finish();
            }
        }, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
