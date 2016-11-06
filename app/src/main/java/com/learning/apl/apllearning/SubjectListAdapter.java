package com.learning.apl.apllearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.Subject;

/**
 * Created by shiva on 6/11/16.
 */

public class SubjectListAdapter extends ArrayAdapter<SubjectModel> {

    public SubjectListAdapter(Context context, ArrayList<SubjectModel> subjects){
        super(context, 0, subjects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubjectModel subject = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_list_item, parent, false);
        }

        TextView subjectName = (TextView) convertView.findViewById(R.id.subjectListText);
        subjectName.setText(subject.getName());

        return  convertView;
    }
}
