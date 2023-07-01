package com.ampesoftware.haftsin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainAppActivity extends Activity {
	SharedPreferences sppos;
	Editor edpos;
	dbhelper dbh=new dbhelper(this);
	int position;
	int counter;
	boolean editmode;
	String sharestr;
	public static String currfont;
	TextView txtmsg;
	TextView txtcounter;
	ImageButton btnmine;
	ImageButton btnpos;
	ImageButton btnnext;
	ImageButton btnprev;
	ImageButton btnshare;
	ImageButton btncopy;
	ImageButton btnedit;
	EditText txteditmsg;
	Button btncancel;
	Button btnsave;
	Button btnfontcancel;
	Button btnfontsave;
	RadioButton radiokoodak;
	RadioButton radiohoma;
	RadioGroup radiogroup;
	ImageButton btngoto;
	ImageButton btncancelgoto;
	NumberPicker np;
	
	public AlertDialog alt;
	public AlertDialog.Builder altb;
	View view;
	
	public AlertDialog altfont;
	public AlertDialog.Builder altbfont;
	View viewfont;
	
	public AlertDialog altgoto;
	public AlertDialog.Builder altbgoto;
	View viewgoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_main_app);
        sppos=getSharedPreferences("myposition", this.MODE_PRIVATE);
        edpos=sppos.edit();
        editmode=false;
        
    
        if(sppos.contains("sp-position"))
        {
        	position=sppos.getInt("sp-position", 0);
        }else{
        	position=0;
        	edpos.putInt("sp-position", position);
        	edpos.commit();
        }
        
        txtcounter=(TextView)findViewById(R.id.txtcounter);
        txtmsg=(TextView)findViewById(R.id.txtmsg1);
        btnmine=(ImageButton)findViewById(R.id.btndec);
        btnpos=(ImageButton)findViewById(R.id.btninc);
        btnnext=(ImageButton)findViewById(R.id.btnnext);
        btnprev=(ImageButton)findViewById(R.id.btnprev);
        btnshare=(ImageButton)findViewById(R.id.btnshare);
        btnedit=(ImageButton)findViewById(R.id.btnedit);
        btncopy=(ImageButton)findViewById(R.id.btncopy);
        
        
	    txtmsg.setMovementMethod(new ScrollingMovementMethod());
		dbh.openDataBase();
		txtmsg.setText(dbh.getdata(position,1));
		sharestr=dbh.getdata(position,1);
		counter=position+1;
		txtcounter.setText(counter+"");
		
		altb=new AlertDialog.Builder(this);
		LayoutInflater inf=this.getLayoutInflater();
		view=inf.inflate(R.layout.editarea, null);		
		altb.setView(view);
		alt=altb.create();
		txteditmsg=(EditText)view.findViewById(R.id.txteditbox);
		btncancel=(Button)view.findViewById(R.id.btncancel);
		btnsave=(Button)view.findViewById(R.id.btnsave);
		txteditmsg.setText(dbh.getdata(position,1));
		
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
	    txtcounter.setTypeface(typeFace);
	    txtmsg.setTypeface(typeFace);
	    txtcounter.setTextSize(MainMenuActivity.fontsize);
	    txtmsg.setTextSize(MainMenuActivity.fontsize);
	    
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
				    txtcounter.setTypeface(typeFace);
				    txtmsg.setTypeface(typeFace);
				    MainMenuActivity.edfont.putString("sp-font", currfont);
				    MainMenuActivity.edfont.commit();
					Toast.makeText(getApplicationContext(), "تغییر فونت با موفقیت لنجام شد",Toast.LENGTH_SHORT).show();
					altfont.cancel();
			    }else{
			    	currfont="fonts/BHoma.ttf";
					typeFace=Typeface.createFromAsset(getAssets(),currfont);
				    txtcounter.setTypeface(typeFace);
				    txtmsg.setTypeface(typeFace);
				    MainMenuActivity.edfont.putString("sp-font", currfont);
				    MainMenuActivity.edfont.commit();
		        	Toast.makeText(getApplicationContext(), "تغییر فونت با موفقیت لنجام شد",Toast.LENGTH_SHORT).show();
		        	altfont.cancel();
			    }

			
			}
		});

		
		btnsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dbh.editmsg(txteditmsg.getText().toString(), Integer.parseInt(dbh.getdata(position,0).toString()));
				Toast.makeText(getApplicationContext(), "متن با موفقیت ویرایش گردید",Toast.LENGTH_SHORT).show();
				editmode=false;
				txtmsg.setText(dbh.getdata(position,1));
				txteditmsg.setText(dbh.getdata(position,1));
				alt.cancel();
			}
		});
		
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				alt.cancel();
				editmode=false;
			}
		});
		
		altbgoto=new AlertDialog.Builder(this);
		LayoutInflater infgoto=this.getLayoutInflater();
		viewgoto=inf.inflate(R.layout.gotorecord, null);		
		altbgoto.setView(viewgoto);
		altgoto=altbgoto.create();
		btngoto=(ImageButton)viewgoto.findViewById(R.id.btngoto);
		btncancelgoto=(ImageButton)viewgoto.findViewById(R.id.btncanclegoto);
		np=(NumberPicker)viewgoto.findViewById(R.id.numberPicker1);
		np.setMaxValue(86);
		np.setMinValue(1);
		np.setValue(counter);
		
		btncancelgoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				altgoto.cancel();
			}
		});
		
		btngoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter=np.getValue();
				position=counter-1;
				txtmsg.setText(dbh.getdata(position,1));
				txteditmsg.setText(dbh.getdata(position,1));
				sharestr=dbh.getdata(position,1);
				counter=position+1;
				txtcounter.setText(counter+"");
				altgoto.cancel();
			}
		});

		
		btnnext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(position<85){
				sharestr="";
				position++;
				txtmsg.setText(dbh.getdata(position,1));
				txteditmsg.setText(dbh.getdata(position,1));
				sharestr=dbh.getdata(position,1);
				counter=position+1;
				txtcounter.setText(counter+"");
				
				}
			}
		});
		
		btnprev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(position>0){
				position--;
				sharestr="";
				txtmsg.setText(dbh.getdata(position,1));
				txteditmsg.setText(dbh.getdata(position,1));
				sharestr=dbh.getdata(position,1);
				counter=position+1;
				txtcounter.setText(counter+"");}
			}
		});
		
		btnpos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(MainMenuActivity.fontsize<45){
					MainMenuActivity.fontsize+=1;
				    txtcounter.setTextSize(MainMenuActivity.fontsize);
				    txtmsg.setTextSize(MainMenuActivity.fontsize);
					radiokoodak.setTextSize(MainMenuActivity.fontsize);
					radiohoma.setTextSize(MainMenuActivity.fontsize);
				}
			}
		});
		
		btnmine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(MainMenuActivity.fontsize>8){
					MainMenuActivity.fontsize-=1;
				    txtcounter.setTextSize(MainMenuActivity.fontsize);
				    txtmsg.setTextSize(MainMenuActivity.fontsize);
					radiokoodak.setTextSize(MainMenuActivity.fontsize);
					radiohoma.setTextSize(MainMenuActivity.fontsize);
				}
			}
		});
		
		btnshare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	 	        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
	 	        sharingIntent.setType("text/plain");
	 	        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
	 	        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sharestr);
	 	        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.hello_world)));
			}
		});
		
		btncopy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Clipboard_Manager clp=new Clipboard_Manager();
				clp.copyToClipboard(getApplicationContext(), sharestr);
				Toast.makeText(getApplicationContext(), "متن کپی گردید، شما میتوانید از این متن در دیگر نرم افزار های پیام رسان استفاده نمایید",Toast.LENGTH_SHORT).show();
			}
		});
		
		btnedit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(editmode)
				{

				}else{
					
					alt.show();
					editmode=true;
					
				}
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_app, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected

		case R.id.action_backmsg:
        	onBackPressed();
            break;
		case R.id.selfonts:
			altfont.show();
			break;
		case R.id.gotomsg:
			altgoto.show();
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    } 
	
@Override
public void onBackPressed() {
			MainMenuActivity.ed.putFloat("sp-fontsize", MainMenuActivity.fontsize);
			MainMenuActivity.ed.commit();
        	edpos.putInt("sp-position", position);
        	edpos.commit();
			finish();
}

}
