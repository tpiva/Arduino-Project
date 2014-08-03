package com.brothers.spendcontrol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.brothers.spendcontrol.db.DatabaseOperationsDAO;
import com.brothers.spendcontrol.entities.Spends;
import com.brothers.spendcontrol.utils.Mask;

@SuppressLint("SimpleDateFormat")
public class SpendsActivity extends Activity {
	
	private static final String CURRENT_DATE = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	private static List<Spends> spendsRegister;
	private static Bundle actualBundle;
	private static Spends actualSpend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spends);

		final DatabaseOperationsDAO dao = DatabaseOperationsDAO.getInstance(this);
		actualBundle = savedInstanceState;

		final Button buttonAdd = (Button) findViewById(R.id.button_add_spend);

		buttonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				actualSpend = null;
				onCreateDialog(actualBundle,dao);
			}

		});

		updateListView(dao);
	}
	
	private void updateListView(DatabaseOperationsDAO dao) {
		if(spendsRegister != null) {
			spendsRegister.clear();
		}
		//return all not pay spend
		spendsRegister = dao.returnAllNotPaySpends();
		
		ArrayAdapter<Spends> adapter = new InframeRegisterAdapter(this, R.layout.list_inframe_item, spendsRegister);
		ListView listView = (ListView) findViewById(R.id.list_view_inframe);
		adapter.setNotifyOnChange(true);
		listView.setAdapter(adapter);
		
		registerForContextMenu(listView);
		
	}
	
	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,  
			ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		MenuInflater m = getMenuInflater();  
		m.inflate(R.menu.menu_listview_inframe_register, menu);  
	}  
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		final DatabaseOperationsDAO dao = DatabaseOperationsDAO.getInstance(this);
		ListView listView = (ListView) findViewById(R.id.list_view_inframe);
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		switch(item.getItemId()){  
		case R.id.edit_inframe_item:  
			actualSpend= (Spends) listView.getAdapter().getItem((int) info.position );  
			onCreateDialog(actualBundle, dao);
			
			return true;  
		case R.id.delete_inframe_item:
			Spends spendToDelete = (Spends) listView.getAdapter().getItem((int) info.position );
			dialogOfConfirmationPayOrDeleteSpend(spendToDelete,dao,1);
			return true;
			
		case R.id.pay_inframe_item:
			Spends paySpend = (Spends) listView.getAdapter().getItem((int) info.position );
			dialogOfConfirmationPayOrDeleteSpend(paySpend,dao,2);
			return true;
		
		}  
		return super.onContextItemSelected(item);  
	}  

	public void onCreateDialog(Bundle savedInstanceState,final DatabaseOperationsDAO dao) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Get the layout inflater
		LayoutInflater inflater = this.getLayoutInflater();
		
		final View layout = inflater.inflate(R.layout.activity_spend_register, null);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		final EditText inframeName = (EditText) layout.findViewById(R.id.inframe);
		final EditText inframeValue = (EditText) layout.findViewById(R.id.value_inframe);
		final EditText duoDateValue = (EditText) layout.findViewById(R.id.value_duoDate);
		duoDateValue.addTextChangedListener(Mask.insert("##/##/####", duoDateValue));
		
		if(actualSpend != null) {
			//TODO possible arrayIndexOfBounds
			inframeName.setText(actualSpend.getNameOfSpend(),TextView.BufferType.EDITABLE);
			inframeValue.setText(String.valueOf(actualSpend.getValue()),TextView.BufferType.EDITABLE);
			duoDateValue.setText(actualSpend.getDuoDate());
		}

		builder.setCancelable(false);
		builder.setView(layout)
		// Add action buttons
		.setPositiveButton(actualSpend == null ? R.string.button_finish : 
															R.string.button_edit, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				//it's absolute right an exist inframe register.
//				Inframe inframe = dao.returnAllInframes().get(0);
				
				//get name of spend and value
				String nameOfInframe = inframeName.getText().toString();
				String duoDate = duoDateValue.getText().toString();
				String valueInString = inframeValue.getText().toString();
				Double value = 0.0;
				
				//all strings never be null
				if(!valueInString.equals("")){
					value = Double.valueOf(inframeValue.getText().toString());			
				}
				
				//if some value is null show message for user
				//TODO show error while all is null
				//if(!nameOfInframe.equals("") && !value.equals(0.0) && duoDateValue.equals("")) {
					//create a new spend to save on bd
					Spends newSpend = null;
					if(actualSpend == null) {
						newSpend = new Spends("false", null, nameOfInframe, null, null, null, value, CURRENT_DATE, duoDate);
						dao.saveSpends(newSpend);
					} else {
						newSpend = new Spends("false", null, nameOfInframe, null, null, null, actualSpend.getIdSpends(), 
								value, CURRENT_DATE, duoDate);
						dao.updateSpend(newSpend);
					}
					updateListView(dao);				
					programNotification(duoDate); 
				} 
			//}

			
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				//dismiss dialog
				dialog.dismiss();
			}
		});      
		
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	private void dialogOfConfirmationPayOrDeleteSpend(final Spends forDeleteOrUpdate,final DatabaseOperationsDAO dao,final int option) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getResources().getString(option == 1 ? R.string.delete_spend_information : 
			R.string.pay_spend_information));
        builder.setCancelable(true);
        builder.setPositiveButton(getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	if(option == 1) {
            		//delete from database
            		dao.deleteSpend(forDeleteOrUpdate);
            	} else {
            		//update spend and remove from listview
            		//using now payday for true or false (it set send pay or not pay)
            		forDeleteOrUpdate.setPayDay("true");
            		dao.updateSpend(forDeleteOrUpdate);
            	}
            	updateListView(dao);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	dialog.cancel();
            }
        });
        
        AlertDialog alert = builder.create();
        alert.show();
	}
	
	private void programNotification(String duoDate) {
		PendingIntent mAlarmSender = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getApplicationContext(), AlarmReceiver.class), 0);
		Date date = null;
		
		// SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy");   //PARA USAR
		SimpleDateFormat  format = new SimpleDateFormat(); //PARA TESTAR
		
		try {
			//date = format.parse(duoDate); //PARA USAR
			date = format.parse("02/07/2014 15:06"); //PARA TESTAR
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Set the alarm to 10 seconds from now
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        long firstTime = c.getTimeInMillis();
        // Schedule the alarm!
        AlarmManager am = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, firstTime, mAlarmSender);
	}
}
