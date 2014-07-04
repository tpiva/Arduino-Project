package com.brothers.spendcontrol;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.brothers.spendcontrol.db.DatabaseOperationsDAO;
import com.brothers.spendcontrol.entities.Inframe;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button buttonSpend = (Button) findViewById(R.id.button_spend);
		final Button buttonInframe = (Button) findViewById(R.id.button_inframe);

		// database object
		DatabaseOperationsDAO dao = DatabaseOperationsDAO
				.getInstance(getApplicationContext());

		buttonInframe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intentInframe = new Intent(getApplicationContext(),
						InframeActivity.class);
				startActivity(intentInframe);
			}

		});

		buttonSpend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intentSpends = new Intent(getApplicationContext(),
						SpendsActivity.class);
				startActivity(intentSpends);
			}

		});

		// verify if option can be show for user.
		boolean sucessInframeSave = false;

		// if bd have inframe than release ui components
		List<Inframe> inframe = dao.returnAllInframes();

		if (inframe != null && !inframe.isEmpty()) {
			sucessInframeSave = true;
		} else {
			sucessInframeSave = ((getIntent().getFlags()) == 1) ? true : false;
		}

		// get "dont_show_dave_message" when user cancel changes of inframe.
		boolean dontShowMessageOfSucess = getIntent().getBooleanExtra(
				"dont_show_dave_message", false);

		buttonSpend.setEnabled(sucessInframeSave);

		// show message of sucess
		if (sucessInframeSave && !dontShowMessageOfSucess) {
			Toast messageSucessInframe = Toast.makeText(this, getResources()
					.getString(R.string.sucess_save_inframe),
					Toast.LENGTH_SHORT);
			messageSucessInframe.show();
		}
	}
}