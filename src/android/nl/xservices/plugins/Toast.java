package nl.xservices.plugins;

//import nl.xservices.plugins.FakeR;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;
//import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

/*
	// TODO nice way for the Toast plugin to offer a longer delay than the default short and long options
	// TODO also look at https://github.com/JohnPersano/Supertoasts
	new CountDownTimer(6000, 1000) {
	  public void onTick(long millisUntilFinished) {toast.show();}
	  public void onFinish() {toast.show();}
	}.start();
 */
public class Toast extends CordovaPlugin {

	private static final String ACTION_SHOW_EVENT = "show";
	private FakeR fakeR;

	public Bitmap getBitmapFromURL(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap bmap = BitmapFactory.decodeStream(input);

			return bmap ;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if (ACTION_SHOW_EVENT.equals(action)) {
			final String message = args.getString(0);
			final String duration = args.getString(1);
			final String position = args.getString(2);
			final String image = args.getString(3);
			
			final Bitmap bmap = getBitmapFromURL(image);
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				public void run() {
					Context context = cordova.getActivity().getApplicationContext();
					Resources resources = context.getResources();                       
                        		String packageName = context.getPackageName();
					
					LayoutInflater inflater = LayoutInflater.from(context);
					//fakeR = new FakeR(webView.getContext());
        				
					//View layout = inflater.inflate(android.R.layout.custom_toast,(ViewGroup)findViewById(android.R.id.toast_layout));
					View layout = inflater.inflate(resources.getIdentifier("custom_toast","layout",packageName),(ViewGroup) findViewById(resources.getIdentifier("toast_layout","id",packageName)));
					//TextView text = (TextView) layout.findViewById(android.R.id.text);
					TextView text = (TextView) layout.findViewById(resources.getIdentifier("text","id",packageName));
					//ImageView imageView = (ImageView) layout.findViewById(android.R.id.image);
					ImageView imageView = (ImageView) layout.findViewById(resources.getIdentifier("image","id",packageName));
					imageView.setImageBitmap(bmap);
					
					text.setText(message); //Message shown in Custom Toast
					android.widget.Toast toast = new android.widget.Toast(context);
					
					if ("top".equals(position)) {
						toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
					} else  if ("bottom".equals(position)) {
						toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
					} else if ("center".equals(position)) {
						toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
					} else {
						callbackContext.error("invalid position. valid options are 'top', 'center' and 'bottom'");
						return;
					}
					if ("short".equals(duration)) {
						toast.setDuration(android.widget.Toast.LENGTH_SHORT);
					} else if ("long".equals(duration)) {
						toast.setDuration(android.widget.Toast.LENGTH_LONG);
					} else {
						callbackContext.error("invalid duration. valid options are 'short' and 'long'");
						return;
					}
					toast.setView(layout);
					toast.show();
				
					callbackContext.success();
				}
			});
			
			return true;
		} else {
			callbackContext.error("toast." + action + " is not a supported function. Did you mean '" + ACTION_SHOW_EVENT + "'?");
			return false;
		}
	}
}
