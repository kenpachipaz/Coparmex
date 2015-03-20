package com.coparmex.coparmexbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemUserAdapter extends BaseAdapter {

	protected Activity activity;
	protected ArrayList<ItemUsuarios> items;
	private Bitmap bitMap;

	public ItemUserAdapter(Activity activity, ArrayList<ItemUsuarios> items) {
		this.activity = activity;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		 View vi=contentView;
         
		    if(contentView == null) {
		      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      vi = inflater.inflate(R.layout.lista_usuarios, null);
		    }
		             
		    ItemUsuarios item = items.get(position);
		         
		    ImageView image = (ImageView) vi.findViewById(R.id.imagen);
		    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
		    image.setImageDrawable(activity.getResources().getDrawable(imageResource));
		         
		    TextView nombre = (TextView) vi.findViewById(R.id.nombre);
		    nombre.setText(item.getNombre());
		    bitMap= BitmapFactory.decodeResource(activity.getResources(), imageResource);
		    image.setImageBitmap(getRoundedCornerImage(bitMap));
		         
		    TextView tipo = (TextView) vi.findViewById(R.id.tipo);
		    tipo.setText(item.getTipo());
		 
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
