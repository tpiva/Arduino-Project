package com.brothers.spendcontrol.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.brothers.spendcontrol.R;

public class UtilsDialogs {
	
	public static void dialogOfUpdateInframe(Context context, int idOfStringResource ) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getResources().getString(idOfStringResource));
        builder.setCancelable(true);
        builder.setPositiveButton(context.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        
        AlertDialog alert = builder.create();
        alert.show();
	}

}
