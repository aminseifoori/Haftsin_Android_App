package com.ampesoftware.haftsin;


import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;


public class DecodingImages  {
	

	public static int mywidth;
	public static int myheight;

	
	
	public void checkdimensions (Context context ,int imageid, int scrw,int scrh){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), imageid, options);
		double imgorgheight = options.outHeight;
		double imgorgwidth = options.outWidth;
		double ratio=imgorgwidth/imgorgheight;
	    
		mywidth=scrw;
	    double myheightd= mywidth/ratio;
	    myheight=(int) myheightd;
	}
	
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) { 
    // Raw height and width of image 
    final int height = options.outHeight; 
    final int width = options.outWidth; 
    int inSampleSize = 1; 
 
    if (height > reqHeight || width > reqWidth) { 
 
        final int halfHeight = height / 2; 
        final int halfWidth = width / 2; 
 
        // Calculate the largest inSampleSize value that is a power of 2 and keeps both 
        // height and width larger than the requested height and width. 
        while ((halfHeight / inSampleSize) > reqHeight 
                && (halfWidth / inSampleSize) > reqWidth) { 
            inSampleSize *= 2; 
        } 
    } 
 
    return inSampleSize; 
}

    
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, 
            int reqWidth, int reqHeight) { 
     
        // First decode with inJustDecodeBounds=true to check dimensions 
        final BitmapFactory.Options options = new BitmapFactory.Options(); 
        options.inJustDecodeBounds = true; 
        BitmapFactory.decodeResource(res, resId, options); 
     
        // Calculate inSampleSize 
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight); 
     
        // Decode bitmap with inSampleSize set 
        options.inJustDecodeBounds = false; 
        return BitmapFactory.decodeResource(res, resId, options); 
    }
    
    
    public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;
        private Resources res2;
        private int w;

        public BitmapWorkerTask(ImageView imageView, Resources res, int widthh) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
            w=widthh;
            res2=res;
        }

        

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return decodeSampledBitmapFromResource(res2, data, w, w);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                final BitmapWorkerTask bitmapWorkerTask =
                        getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }


}
    
	public void loadBitmap(int resId, ImageView imageView, Resources res, Bitmap mplaceholder, int size ) {
	    if (cancelPotentialWork(resId, imageView,res,mplaceholder)) {
	    	DecodingImages tt=new DecodingImages();
	        final DecodingImages.BitmapWorkerTask task = tt.new BitmapWorkerTask(imageView,res,size);
	        final AsyncDrawable asyncDrawable =
	                new AsyncDrawable(res,mplaceholder ,task);
	        imageView.setImageDrawable(asyncDrawable);
	        task.execute(resId);
	    }
	}
	
	public static boolean cancelPotentialWork(int data, ImageView imageView, Resources res,Bitmap bitmap) {
	    final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

	    if (bitmapWorkerTask != null) {
	        final int bitmapData = bitmapWorkerTask.data;
	        // If bitmapData is not yet set or it differs from the new data
	        if (bitmapData == 0 || bitmapData != data) {
	            // Cancel previous task
	            bitmapWorkerTask.cancel(true);
	        } else {
	            // The same work is already in progress
	            return false;
	        }
	    }
	    // No task associated with the ImageView, or an existing task was cancelled
	    return true;

	}

	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		   if (imageView != null) {
		       final Drawable drawable = imageView.getDrawable();
		       if (drawable instanceof AsyncDrawable) {
		           final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
		           return asyncDrawable.getBitmapWorkerTask();
		       }
		    }
		    return null;
		}
    }     
    

