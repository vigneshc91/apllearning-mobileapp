package com.learning.apl.apllearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shiva on 6/11/16.
 */

public class MaterialListAdapter extends ArrayAdapter<MaterialModel> {

    public MaterialListAdapter(Context context, ArrayList<MaterialModel> materials){
        super(context, 0, materials);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MaterialModel material = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.material_list_item, parent, false);
        }

        TextView titleText = (TextView) convertView.findViewById(R.id.materialTitleText);
        TextView descriptionText = (TextView) convertView.findViewById(R.id.materialDescriptionText);

        titleText.setText(material.getTitle());
        if(!material.getDescription().equals("null")){
            descriptionText.setText(material.getDescription());
        } else {
            descriptionText.setText("");
        }

        return convertView;
    }
}
