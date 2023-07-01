package com.ampesoftware.haftsin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainGridview extends Activity {
	public int tumbsize;
	String[] imageUrls=null; 
	
	DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
        
		
		switch(ImageStore.wm)
        {
        case 1:
        	imageUrls= Constants.Sabzeh;
	        break;
        case 3:
        	imageUrls= Constants.egges;
	        break;
        case 4:
        	imageUrls= Constants.Haftsin;
	        break;
        }
		
		
		setContentView(R.layout.activity_main_gridview);
		GridView gridview = (GridView) findViewById(R.id.gridview);

	    DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int wwidth = displaymetrics.widthPixels;
	    int cw=(wwidth-15)/3;
	    gridview.setColumnWidth(cw);

	    gridview.setAdapter(new ImageAdapter(this));
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Intent in=new Intent(MainGridview.this, MainViewFullScreen.class);
	        	in.putExtra("imagename",position);
	        	startActivity(in);
	        }
	    });

	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
         case R.id.action_grideback:
        	  finish();
        	 break;
          default:
          break;
        }
    	return super.onOptionsItemSelected(item);
    }
	
    @Override
    protected void onDestroy() {
    super.onDestroy();

    //unbindDrawables(findViewById(R.id.RootView));
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_gridview, menu);
		return true;
	}
	
	public class ImageAdapterold extends BaseAdapter {
	    private Context mContext;

	    public ImageAdapterold(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        switch(ImageStore.wm)
	        {
	        case 1:
		        //return ImageStore.sabzeh.length;
	        case 2:
	        			        
	        case 3:
	        	//return ImageStore.eggsdesign.length;
	        case 4:
	        	return ImageStore.haftsin.length;
	        }
	        return 0;

	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	DecodingImages dcimg=new DecodingImages();
	    	ImageView imageView;
     
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	        	
	            DisplayMetrics displaymetrics = new DisplayMetrics();
	            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	            int height = displaymetrics.heightPixels;
	            int wwidth = displaymetrics.widthPixels;
	            
	            tumbsize=(wwidth-15)/3;
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(tumbsize, tumbsize));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(10,10,10,10);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        

	        switch(ImageStore.wm)
	        {
	        case 1:
	            try{
	            //imageView.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(), ImageStore.sabzeh[position], tumbsize, tumbsize));
	            //dcimg.loadBitmap(ImageStore.sabzeh[position], imageView,getResources(),null,tumbsize);
	            //imageView.setBackgroundColor(Color.rgb(0, 0, 0));
	            }catch(Exception e){
	            	
	            }
		        break;
	        case 2:
		        
		        break;
	        case 3:
	        	try{
	        	//imageView.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(), ImageStore.eggsdesign[position], tumbsize, tumbsize));
	        	//dcimg.loadBitmap(ImageStore.eggsdesign[position], imageView,getResources(),null,tumbsize);
	        	//imageView.setBackgroundColor(Color.rgb(0, 0, 0));
	        	}catch(Exception e){
	        		
	        	}
		        break;
	        case 4:
	        	try{
	        	//imageView.setImageBitmap(dcimg.decodeSampledBitmapFromResource(getResources(), ImageStore.haftsin[position], tumbsize, tumbsize));
	        	dcimg.loadBitmap(ImageStore.haftsin[position], imageView,getResources(),null,tumbsize);
	        	imageView.setBackgroundColor(Color.rgb(0, 0, 0));
	        	}catch (Exception e) {
				}
		        break;
	        }

	        return imageView;
	    }
	    
	    }    

	public class ImageAdapter extends BaseAdapter {

	    private Context mContext;
	    private LayoutInflater inflater;
	    public ImageAdapter(Context c) {
	        mContext = c;
	        inflater = LayoutInflater.from(c);
	    }

		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			View view = convertView;
			if (view == null) {
				
				view = inflater.inflate(R.layout.item_grid_image, parent, false);
				holder = new ViewHolder();
				assert view != null;
				holder.imageView = (ImageView) view.findViewById(R.id.image);
				holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			ImageLoader.getInstance()
					.displayImage(imageUrls[position], holder.imageView, options, new SimpleImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							holder.progressBar.setProgress(0);
							holder.progressBar.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
							holder.progressBar.setVisibility(View.GONE);
						}

						@Override
						public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
							holder.progressBar.setVisibility(View.GONE);
						}
					}, new ImageLoadingProgressListener() {
						@Override
						public void onProgressUpdate(String imageUri, View view, int current, int total) {
							holder.progressBar.setProgress(Math.round(100.0f * current / total));
						}
					});

			return view;
		}
	}	
	
	static class ViewHolder {
		ImageView imageView;
		ProgressBar progressBar;
	}

}
