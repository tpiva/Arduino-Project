package com.brothers.spendcontrol;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.brothers.spendcontrol.db.DatabaseOperationsDAO;
import com.brothers.spendcontrol.entities.Inframe;

public class InframeActivity extends Activity {
	
	private static boolean isInframeValueExists = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inframe);
		
		final DatabaseOperationsDAO dao = DatabaseOperationsDAO.getInstance(getApplicationContext());

		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/

		final EditText editText = (EditText) findViewById(R.id.edit_text_number);
		final String messageWithoutInframe = getResources().getString(R.string.message_without_inframe_error);
		Button buttonFinish = (Button) findViewById(R.id.button_finish);
		
		//set value of inframe and tell for user that an inframe exists and if it'll want to update inframe.
		fillInframeValue(editText, dao); //TODO lançar exceção quando não existir
		
		if(isInframeValueExists) {
			dialogOfUpdateInframe();
		}
		
		buttonFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentMain = new Intent(v.getContext(), MainActivity.class);
				
				if(!validateInputInframe(editText)) {
					//save inframe
					if(!isInframeValueExists) {
						dao.saveInframe(new Inframe(1,Double.parseDouble(editText.getText().toString())));
					} else {
						dao.updateInframe(new Inframe(1,Double.parseDouble(editText.getText().toString())));
					}
					
					intentMain.setFlags(1);
					intentMain.putExtra("dont_show_dave_message", false);
					startActivity(intentMain);
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		            builder.setMessage(messageWithoutInframe);
		            builder.setCancelable(true);
		            builder.setPositiveButton(getResources().getString(R.string.ok),
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
		            
		            AlertDialog alert = builder.create();
		            alert.show();
				}
			}
		});
	}

	private final boolean validateInputInframe(EditText editText) {
		boolean isEmptyInframe = false;
		String inframeText = editText.getText().toString();
		
		if(inframeText != null && inframeText.equals("")) {
			isEmptyInframe = true;
		}
		
		return isEmptyInframe;
		
	}
	
	private void fillInframeValue(EditText edit, DatabaseOperationsDAO dao) {
		List<Inframe> inframes = dao.returnAllInframes();
		
		if(inframes != null && !inframes.isEmpty()) {
			edit.setText(String.valueOf(inframes.get(0).getInframeValue()));
			isInframeValueExists = true;
		}
	}
	
	private void dialogOfUpdateInframe() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.update_inframe));
        builder.setCancelable(true);
        builder.setPositiveButton(getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
            	intentMain.setFlags(1);
            	intentMain.putExtra("dont_show_dave_message", true);
				startActivity(intentMain);
            }
        });
        
        AlertDialog alert = builder.create();
        alert.show();
	}

}
