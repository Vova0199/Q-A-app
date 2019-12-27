package com.vova_ioter.questionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vova_ioter.questionapp.Model.AnswerModel;

import java.util.List;

/**
 * Created by Vova0199 on 07.05.2018.
 */

public class AnswerAdapter extends ArrayAdapter<AnswerModel> {

    List<AnswerModel> contactList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public AnswerAdapter(Context context, List<AnswerModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        contactList = objects;
    }

    @Override
    public AnswerModel getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        AnswerModel item = getItem(position);

        vh.textViewName.setText(item.getAnswer());
        vh.textViewEmail.setText(item.getQuestion());
        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textViewName;
        public final TextView textViewEmail;

        private ViewHolder(RelativeLayout rootView, TextView textViewName, TextView textViewEmail) {
            this.rootView = rootView;
            this.textViewName = textViewName;
            this.textViewEmail = textViewEmail;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewEmail = rootView.findViewById(R.id.textViewName);
            TextView textViewName = rootView.findViewById(R.id.textViewEmail);
            return new ViewHolder(rootView, textViewName, textViewEmail);
        }

    }

}
