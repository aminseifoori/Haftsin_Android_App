package com.ampesoftware.haftsin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainMenuActivity extends Activity {
	ImageButton btn1;
	ImageButton btn2;
	ImageButton btn3;
	ImageButton btn4;
	ImageButton btn5;
	ImageButton btn6;
	public static SharedPreferences sp;
	public static Editor ed;
	public static SharedPreferences spfont;
	public static Editor edfont;
	public static float fontsize;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		btn1=(ImageButton)findViewById(R.id.btn1);
		btn2=(ImageButton)findViewById(R.id.btn2);
		btn3=(ImageButton)findViewById(R.id.btn3);
		btn4=(ImageButton)findViewById(R.id.btn4);
		btn5=(ImageButton)findViewById(R.id.btn5);
		btn6=(ImageButton)findViewById(R.id.btn6);
		
        sp=getSharedPreferences("myfontsize", this.MODE_PRIVATE);
        spfont=getSharedPreferences("myfont", this.MODE_PRIVATE);
        ed=sp.edit();
        edfont=spfont.edit();
        
        getDeviceResolution(getApplicationContext());      
        
        if(sp.contains("sp-fontsize"))
        {
        	fontsize=sp.getFloat("sp-fontsize", 0);
        }else{
        	ed.putFloat("sp-fontsize", fontsize);
        	ed.commit();
        }
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte=new Intent(MainMenuActivity.this,MainGridview.class);
				ImageStore.wm=1;
				startActivity(inte);
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte=new Intent(MainMenuActivity.this,MainSabzehActivity.class);
				startActivity(inte);
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte=new Intent(MainMenuActivity.this,MainGridview.class);
				ImageStore.wm=3;
				startActivity(inte);
			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte=new Intent(MainMenuActivity.this,MainGridview.class);
				ImageStore.wm=4;
				startActivity(inte);
			}
		});
		
		btn5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte=new Intent(MainMenuActivity.this,MainAppActivity.class);
				startActivity(inte);
			}
		});
		
		btn6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte=new Intent(MainMenuActivity.this,MainView.class);
				ImageStore.wm=6;
				startActivity(inte);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	private void getDeviceResolution(Context context)
    {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        switch (density)
        {
            case DisplayMetrics.DENSITY_MEDIUM:
                fontsize=18;
                break;
            case DisplayMetrics.DENSITY_HIGH:
            	fontsize=20;
            	break;
            case DisplayMetrics.DENSITY_LOW:
            	fontsize=16;
            	break;
            case DisplayMetrics.DENSITY_XHIGH:
            	fontsize=24;
            	break;
            case DisplayMetrics.DENSITY_TV:
            	fontsize=32;
            	break;
            default:
            	fontsize=18;
            	break;
        }

    }
    @Override
    public void onBackPressed() {
    	AlertDialog.Builder alt=new AlertDialog.Builder(this);
    	alt.setTitle("آیا مایل هستید از برنامه خارج شوید؟");
    	alt.setMessage("شما میتوانید با زدن کلید 5 ستاره جهت حمایت از ارائه محتوای فارسی  و حمایت از ما به این برنامه 5 ستاره بدیهد...با تشکر ");
    	alt.setCancelable(true);
    	alt.setPositiveButton("بلی", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {

				finish();
			}
		});
    	alt.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
    	alt.setNeutralButton("5 ستاره", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			
				   Intent goToMarket = new Intent(Intent.ACTION_EDIT,Uri.parse("market://details?id=com.ampesoftware.haftsin"));
				    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				    startActivity(goToMarket); 
			}
		});
    	AlertDialog sltd=alt.create();
    	sltd.show();
      
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
    	case R.id.share:
     		String shareBody = "http://cafebazaar.ir/app/?id=com.ampesoftware.haftsin";
 	        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
 	        sharingIntent.setType("text/plain");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "نرم افزار هفت سین رویایی");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
 	        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.hello_world)));
 	        break;
        // action with ID action_settings was selected
         case R.id.action_exit:
        	 	onBackPressed();
            break;
         case R.id.ourproduct:
 			Intent intent = new Intent(Intent.ACTION_VIEW);
 			intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=ami00254"));
 			startActivity(intent);
			break;
         case R.id.rating:
 			Intent goToMarket = new Intent(Intent.ACTION_EDIT,Uri.parse("market://details?id=com.ampesoftware.haftsin"));
 			goToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 			startActivity(goToMarket); 
        	break;
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    } 

}
