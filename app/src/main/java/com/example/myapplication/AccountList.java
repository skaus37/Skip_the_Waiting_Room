package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AccountList extends ArrayAdapter<User>{

        private Activity context;
        List<User> users;

        public AccountList(Activity context, List<User> users) {
            super(context, R.layout.layout_user_list, users);
            this.context = context;
            this.users = users;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);

            TextView textViewFirstName = (TextView) listViewItem.findViewById(R.id.textViewFirstName);
            TextView textViewLastName = (TextView) listViewItem.findViewById(R.id.textViewLastName);
            TextView textViewAccountType = (TextView) listViewItem.findViewById((R.id.textViewAccountType));


            User user = users.get(position);
            textViewFirstName.setText(user.getFirstName());
            textViewLastName.setText(user.getLastName());
            textViewAccountType.setText(user.getAccountType());
            return listViewItem;
        }
}



