package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDirectorioAdapter extends BaseAdapter{
	protected Activity activity;
	protected ArrayList<ItemDirectorio> items;
	private Bitmap bitMap;
	         
	public ItemDirectorioAdapter(){
		
	}
	  public ItemDirectorioAdapter(Activity activity, ArrayList<ItemDirectorio> items) {
	    this.activity = activity;
	    this.items = items;
	  }
	 
	  public int getCount() {
	    return items.size();
	  }
	 
	  public Object getItem(int position) {
	    return items.get(position);
	  }
	 
	  public long getItemId(int position) {
	    return items.get(position).getId();
	  }
	 
	  public View getView(int position, View contentView, ViewGroup parent) {
	    View vi=contentView;
	         
	    if(contentView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      vi = inflater.inflate(R.layout.list_item_layout, null);
	    }
	             
	    ItemDirectorio item = items.get(position);
	    
	    Typeface fuente = Typeface.createFromAsset(activity.getAssets(), "Fuentes/dispensations.ttf");
	    
	    ImageView image = (ImageView) vi.findViewById(R.id.imageView1);
	    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
	    image.setImageDrawable(activity.getResources().getDrawable(imageResource));
	         
	    TextView nombre = (TextView) vi.findViewById(R.id.textView1);
	    bitMap= BitmapFactory.decodeResource(activity.getResources(), imageResource);
	    image.setImageBitmap(getRoundedCornerImage(bitMap));
	    nombre.setTypeface(fuente);
	    nombre.setText(item.getNombre());
	 
	    return vi;
	  }
	  
	  public static Bitmap getRoundedCornerImage(Bitmap bitmap) {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
			    bitmap.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(output);

			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = 100;

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);

			return output;

			}

}
