package com.brothers.spendcontrol;

import java.util.List;

import com.brothers.spendcontrol.entities.Spends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InframeRegisterAdapter extends ArrayAdapter<Spends> {

	/*
	 * Used to instantiate layout XML file into its corresponding View objects
	 */
	private final LayoutInflater inflater;

	/*
	 * each list item layout ID
	 */
	private final int resourceId;
	
	private List<Spends> objects;

	public InframeRegisterAdapter(Context context, int resource, List<Spends> objects) {
		super(context, resource, objects);
		this.inflater = LayoutInflater.from(context);
		this.resourceId = resource;
		this.objects = objects;
	}
	
	@Override
	public int getCount() {
		return objects.size();
	}
	
	@Override
    public Spends getItem(int position) {
    	return objects.get(position);
    }
	    
	@Override
    public long getItemId(int position) {
    	return position;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// get the person from position
		final Spends spends = getItem(position);

		if (position % 2 == 0) {
			convertView = inflater.inflate(resourceId, parent, false);
		} else {
			convertView = inflater.inflate(R.layout.list_inframe_item, parent, false);
		}

		// get all object from view
		TextView field1 = (TextView) convertView.findViewById(R.id.first_collumn);
		TextView field2 = (TextView) convertView.findViewById(R.id.second_line);
		
		field1.setText(spends.getNameOfSpend());
		field2.setText(String.valueOf(spends.getValue()));

		return convertView;

	}
}
