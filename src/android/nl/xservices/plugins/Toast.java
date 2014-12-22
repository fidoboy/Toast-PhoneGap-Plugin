package nl.xservices.plugins;

import nl.xservices.plugins.FakeR;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.content.res.Resources;

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
					
					//ImageView img = new ImageView();
					//img.setImageBitmap(bmap );
					
					LayoutInflater inflater = LayoutInflater.from(webView.getContext());
					fakeR = new FakeR(this);
        				setContentView(fakeR.getId("layout", "multiselectorgrid"));
					
					//View layout = inflater.inflate(android.R.layout.custom_toast,(ViewGroup)findViewById(android.R.id.toast_layout));
					View layout = inflater.inflate(fakeR.getId("layout", "custom_toast"),(ViewGroup)findViewById(fakeR.getId("id", "toast_layout")));
					//TextView text = (TextView) layout.findViewById(android.R.id.text);
					TextView text = (TextView) layout.findViewById(fakeR.getId("id", "text"));
					//ImageView imageView = (ImageView) layout.findViewById(android.R.id.image);
					ImageView imageView = (ImageView) layout.findViewById(fakeR.getId("id", "image"));
					imageView.setImageBitmap(bmap);
					
					text.setText(message); //Message shown in Custom Toast
					//Toast toast = new Toast(getApplicationContext());
					android.widget.Toast toast = android.widget.Toast(webView.getContext());
					//toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					//toast.show();
					
					//android.widget.Toast toast = new android.widget.Toast(getApplicationContext());
					//android.widget.Toast toast = android.widget.Toast.makeText(webView.getContext(), message, 0);
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
					//toast.setView(img); // <- you custom View
					toast.setView(layout);
					toast.show();
				
					//android.widget.Toast toast = android.widget.Toast.makeText(webView.getContext(), message, 0);
					//toast.show();
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
