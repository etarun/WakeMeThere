package wakemethere.com.wmt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertMessage {



	public static void showMessage(final Context c, final String title,
			final String s) {
		final AlertDialog.Builder aBuilder = new AlertDialog.Builder(c);
		aBuilder.setTitle(title);
		// aBuilder.setIcon(R.drawable.icon);
		aBuilder.setMessage(s);

		aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			
		
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}

		});

		aBuilder.show();
	}

	
	


}
