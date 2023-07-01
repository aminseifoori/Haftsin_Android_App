package com.ampesoftware.haftsin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainSabzehActivity extends Activity {
	
	ImageButton btns1;
	ImageButton btns2;
	ImageButton btns3;
	ImageButton btns4;
	ImageButton btns5;
	ImageButton btns6;
	ImageButton btns7;
	ImageButton btns8;
	Intent in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_sabzeh);
		
		btns1=(ImageButton)findViewById(R.id.btns1);
		btns2=(ImageButton)findViewById(R.id.btns2);
		btns3=(ImageButton)findViewById(R.id.btns3);
		btns4=(ImageButton)findViewById(R.id.btns4);
		btns5=(ImageButton)findViewById(R.id.btns5);
		btns6=(ImageButton)findViewById(R.id.btns6);
		btns7=(ImageButton)findViewById(R.id.btns7);
		btns8=(ImageButton)findViewById(R.id.btns8);
		
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    int screenwidth= metrics.widthPixels;
	    int screenheight= metrics.heightPixels;
	    screenwidth/=6;

	    DecodingImages dcimg=new DecodingImages();
		
        btns1.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic1, screenwidth, screenwidth));
        
        btns2.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic2, screenwidth, screenwidth));
        
        btns3.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic3, screenwidth, screenwidth));
        
        btns4.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic4, screenwidth, screenwidth));
        
        btns5.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic5, screenwidth, screenwidth));

        btns6.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic6, screenwidth, screenwidth));
        
        btns7.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic7, screenwidth, screenwidth));
        
        btns8.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.sic8, screenwidth, screenwidth));
        
        btns1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=11;
				startActivity(in);
			}
		});
        
        btns2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=12;
				startActivity(in);
			}
		});
        
        btns3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=13;
				startActivity(in);
			}
		});
        
        btns4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=14;
				startActivity(in);
			}
		});
        
        btns5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=15;
				startActivity(in);
			}
		});
        
        btns6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=16;
				startActivity(in);
			}
		});
        
        btns7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=17;
				startActivity(in);
			}
		});
        
        btns8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				in=new Intent(MainSabzehActivity.this,MainView.class);
				ImageStore.wm=18;
				startActivity(in);
			}
		});
        

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_sabzeh, menu);
		return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
    	case R.id.action_sabzehback:
    			finish();
 	        break;
      	
          default:
          break;
        }
    	return super.onOptionsItemSelected(item);
    	}
    

}
