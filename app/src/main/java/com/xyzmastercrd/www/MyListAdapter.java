package com.xyzmastercrd.www;

/**
 * Created by GRV on 9/22/2017.
 */

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyListAdapter extends ArrayAdapter<data> {

    List<data> heroList;

    Context context;

    int resource;

    public MyListAdapter(Context context, int resource, List<data> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView sendto = (TextView) view.findViewById(R.id.sendto);
        TextView amount = (TextView) view.findViewById(R.id.amount);
       // Button buttonDelete = (Button) view.findViewById(R.id.buttonDelete);

        data data = heroList.get(position);

        //adding values to the list item
      //  imageView.setImageDrawable(context.getResources().getDrawable(data.getImage()));
        date.setText(data.getDate());
        sendto.setText(data.getName());
        amount.setText("Rs. "+ String.valueOf(data.getAmt()));

        //adding a click listener to the button to remove item from the list
        /*buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
                removeHero(position);
            }
        });*/

        //finally returning the view
        return view;
    }

    private void removeHero(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                heroList.remove(position);

                //reloading the list
                notifyDataSetChanged();
            }
        });

        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}