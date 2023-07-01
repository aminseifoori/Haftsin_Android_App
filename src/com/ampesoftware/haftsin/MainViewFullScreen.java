package com.ampesoftware.haftsin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainViewFullScreen extends Activity {
	ImageView img;
	int position;
    int screenwidth;
    int screenheight;
	DecodingImages dcimg=new DecodingImages();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view_full_screen);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Bundle b=getIntent().getExtras();
		position=b.getInt("imagename");
		img=(ImageView)findViewById(R.id.imageView1);
		
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    screenwidth= metrics.widthPixels;
	    screenheight= metrics.heightPixels;
		switch(ImageStore.wm)
		{
		case 1:
	        dcimg.checkdimensions(getApplicationContext(), ImageStore.sabzeh[position], screenwidth, screenheight);
	        img.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),ImageStore.sabzeh[position], dcimg.mywidth, dcimg.myheight));
			break;
		case 2:

			break;
		case 3:
	        dcimg.checkdimensions(getApplicationContext(), ImageStore.eggsdesign[position], screenwidth, screenheight);
	        img.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),ImageStore.eggsdesign[position], dcimg.mywidth, dcimg.myheight));
			break;
		case 4:
	        dcimg.checkdimensions(getApplicationContext(), ImageStore.haftsin[position], screenwidth, screenheight);
	        img.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),ImageStore.haftsin[position], dcimg.mywidth, dcimg.myheight));
			break;
		}
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_view_full_screen, menu);
		return true;
	}
	
    @Override
    protected void onDestroy() {
    super.onDestroy();

    //unbindDrawables(findViewById(R.id.RootView));
    System.gc();
 
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
         case R.id.action_fsback:
        	  finish();
        	 break;
         case R.id.action_share:
        	Uri imageUri=Uri.parse("android.resource://" + getPackageName()+ "/drawable/" +ImageStore.haftsin[position] );;
     		switch(ImageStore.wm)
    		{
    		case 1:
    			//imageUri = Uri.parse("android.resource://" + getPackageName()+ "/drawable/" +ImageStore.sabzeh[position] );
    			break;
    		case 2:

    			break;
    		case 3:
    			//imageUri = Uri.parse("android.resource://" + getPackageName()+ "/drawable/" +ImageStore.eggsdesign[position] );
    			break;
    		case 4:
    			imageUri = Uri.parse("android.resource://" + getPackageName()+ "/drawable/" +ImageStore.haftsin[position] );
    			break;
    		}    
        	     Intent shareIntent = new Intent();
        	     shareIntent.setAction(Intent.ACTION_SEND);
        	     shareIntent.putExtra(Intent.EXTRA_TEXT, "هفت سین رویایی");
        	     shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        	     shareIntent.setType("image/jpeg");
        	     shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        	     startActivity(Intent.createChooser(shareIntent, "send"));
        	 
          default:
          break;
        }
    	return super.onOptionsItemSelected(item);
    }


    
    

}
