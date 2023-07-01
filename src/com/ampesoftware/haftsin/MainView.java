package com.ampesoftware.haftsin;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainView extends Activity {
	TextView txtname;
	TextView txtrecipe;
    ImageView imageview;
    ImageButton imgplus;
    ImageButton imgmines;
	Button btnfontcancel;
	Button btnfontsave;
	RadioButton radiokoodak;
	RadioButton radiohoma;
	RadioGroup radiogroup;
	dbhelper dbh=new dbhelper(this);
	String sharestr;
	int position;
	DecodingImages dcimg=new DecodingImages();
    int screenwidth;
    int screenheight;
    String currfont;
    
	public AlertDialog altfont;
	public AlertDialog.Builder altbfont;
	View viewfont;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		
		txtname=(TextView)findViewById(R.id.txtname);
		txtrecipe=(TextView)findViewById(R.id.txtrecipe);
		imgplus=(ImageButton)findViewById(R.id.btnplus);
		imgmines=(ImageButton)findViewById(R.id.btnmines);
		
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    screenwidth= metrics.widthPixels;
	    screenheight= metrics.heightPixels;
	    screenwidth/=4;
	    screenheight/=4;

		txtrecipe.setMovementMethod(new ScrollingMovementMethod());
		imageview= (ImageView)findViewById(R.id.imgview);
		
		altbfont=new AlertDialog.Builder(this);
		LayoutInflater inffont=this.getLayoutInflater();
		viewfont=inffont.inflate(R.layout.setfont, null);		
		altbfont.setView(viewfont);
		altfont=altbfont.create();
		btnfontcancel=(Button)viewfont.findViewById(R.id.btncancelfont);
		btnfontsave=(Button)viewfont.findViewById(R.id.btnsavefont);
		radiokoodak=(RadioButton)viewfont.findViewById(R.id.radiokoodak);
		radiohoma=(RadioButton)viewfont.findViewById(R.id.radiohoma);
		radiogroup=(RadioGroup)viewfont.findViewById(R.id.radioGroup1);
		
		radiokoodak.setTextSize(MainMenuActivity.fontsize);
	    Typeface typeFacekoodak=Typeface.createFromAsset(getAssets(),"fonts/BKoodkBd.ttf");
	    radiokoodak.setTypeface(typeFacekoodak);
	    
		radiohoma.setTextSize(MainMenuActivity.fontsize);
	    Typeface typehoma=Typeface.createFromAsset(getAssets(),"fonts/BHoma.ttf");
	    radiokoodak.setTypeface(typeFacekoodak);	
        if(MainMenuActivity.spfont.contains("sp-font"))
        {
        	currfont=MainMenuActivity.spfont.getString("sp-font", " ");
        	if(currfont.equals("fonts/BKoodkBd.ttf")){
        		radiogroup.check(R.id.radiokoodak);
        	}else{
        		radiogroup.check(R.id.radiohoma);
        	}
        }else{
        	currfont="fonts/BKoodkBd.ttf";
        	radiogroup.check(R.id.radiokoodak);
        	MainMenuActivity.edfont.putString("sp-font", currfont);
        	MainMenuActivity.edfont.commit();
        }
        
	    Typeface typeFace=Typeface.createFromAsset(getAssets(),currfont);
	    txtname.setTypeface(typeFace);
	    txtrecipe.setTypeface(typeFace);
	    txtname.setTextSize(MainMenuActivity.fontsize);
	    txtrecipe.setTextSize(MainMenuActivity.fontsize);
	    
		btnfontcancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				altfont.cancel();
			}
		});
		
		btnfontsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Typeface typeFace;
			    if(radiokoodak.isChecked()){
			    	currfont="fonts/BKoodkBd.ttf";
					typeFace=Typeface.createFromAsset(getAssets(),currfont);
					txtname.setTypeface(typeFace);
					txtrecipe.setTypeface(typeFace);
				    MainMenuActivity.edfont.putString("sp-font", currfont);
				    MainMenuActivity.edfont.commit();
					Toast.makeText(getApplicationContext(), "تغییر فونت با موفقیت لنجام شد",Toast.LENGTH_SHORT).show();
					altfont.cancel();
			    }else{
			    	currfont="fonts/BHoma.ttf";
					typeFace=Typeface.createFromAsset(getAssets(),currfont);
					txtname.setTypeface(typeFace);
					txtrecipe.setTypeface(typeFace);
				    MainMenuActivity.edfont.putString("sp-font", currfont);
				    MainMenuActivity.edfont.commit();
		        	Toast.makeText(getApplicationContext(), "تغییر فونت با موفقیت لنجام شد",Toast.LENGTH_SHORT).show();
		        	altfont.cancel();
			    }

			
			}
		});
		
		if(ImageStore.wm==6){
			dbh.openDataBase();
			txtname.setText("تاریخچه نوروز باستانی");
			txtrecipe.setText(dbh.getdatahaftsinstory());
			sharestr=dbh.getdatahaftsinstory();
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.h1, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.h1, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
		}
		
		switch(ImageStore.wm)
		{
		case 11:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(1,2));
			txtname.setText(dbh.getsabzehid(1,1));
			sharestr=dbh.getsabzehid(1,1)+"\n"+dbh.getsabzehid(1,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si1, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si1, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 12:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(2,2));
			txtname.setText(dbh.getsabzehid(2,1));
			sharestr=dbh.getsabzehid(2,1)+"\n"+dbh.getsabzehid(2,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si2, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si2, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 13:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(3,2));
			txtname.setText(dbh.getsabzehid(3,1));
			sharestr=dbh.getsabzehid(3,1)+"\n"+dbh.getsabzehid(3,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si3, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si3, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 14:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(4,2));
			txtname.setText(dbh.getsabzehid(4,1));
			sharestr=dbh.getsabzehid(4,1)+"\n"+dbh.getsabzehid(4,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si4, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si4, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 15:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(5,2));
			txtname.setText(dbh.getsabzehid(5,1));
			sharestr=dbh.getsabzehid(5,1)+"\n"+dbh.getsabzehid(5,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si5, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si5, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 16:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(6,2));
			txtname.setText(dbh.getsabzehid(6,1));
			sharestr=dbh.getsabzehid(6,1)+"\n"+dbh.getsabzehid(6,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si6, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si6, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 17:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(7,2));
			txtname.setText(dbh.getsabzehid(7,1));
			sharestr=dbh.getsabzehid(7,1)+"\n"+dbh.getsabzehid(7,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si7, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si7, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		case 18:
			dbh.openDataBase();
			txtrecipe.setText(dbh.getsabzehid(8,2));
			txtname.setText(dbh.getsabzehid(8,1));
			sharestr=dbh.getsabzehid(8,1)+"\n"+dbh.getsabzehid(8,2);
			dbh.close();			
	        dcimg.checkdimensions(getApplicationContext(), R.drawable.si8, screenwidth, screenheight);
	        imageview.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(),R.drawable.si8, dcimg.mywidth, dcimg.myheight));
	        dbh.close();
			break;
		default:
				break;
		}
		
		


		imgplus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(MainMenuActivity.fontsize<40){
					MainMenuActivity.fontsize+=1;
				txtrecipe.setTextSize(MainMenuActivity.fontsize);
				txtname.setTextSize(MainMenuActivity.fontsize);
				}
			}
		});
		
		imgmines.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(MainMenuActivity.fontsize>8){
					MainMenuActivity.fontsize-=1;
				txtrecipe.setTextSize(MainMenuActivity.fontsize);
				txtname.setTextSize(MainMenuActivity.fontsize);
				}
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
    	case R.id.sharecontent:
 	        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
 	        sharingIntent.setType("text/plain");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sharestr);
 	        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.hello_world)));
 	        break;
        case R.id.action_mainviewback:
    		onBackPressed();			
            break;
        case R.id.selfontsview:
        	altfont.show();
        	
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    }
	@Override
	public void onBackPressed() {
		MainMenuActivity.ed.putFloat("sp-fontsize", MainMenuActivity.fontsize);
		MainMenuActivity.ed.commit();
		finish();

	}
	
    @Override
    protected void onDestroy() {
    super.onDestroy();

    unbindDrawables(findViewById(R.id.RootView));
    System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
        view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
            unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
        ((ViewGroup) view).removeAllViews();
        }
    }

}
