package com.example.cohen.elfa17;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cohen.elfa17.Item;
import com.example.cohen.elfa17.R;
import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class Itemlist extends ArrayAdapter<Item> {
    private Activity context;
    private List<Item> itemList;
    public Itemlist(Activity context, List<Item> itemList) {
        super(context, R.layout.list_layout, itemList);
        this.context=context;
        this.itemList=itemList;





    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem =inflater.inflate(R.layout.list_layout,null , true);
        TextView name =listViewItem.findViewById(R.id.Name2);
        TextView dirug=listViewItem.findViewById(R.id.Dirug2);
        Item item= itemList.get(position);
        name.setText(item.getName());
        dirug.setText(""+item.getDirug());
        return listViewItem;

    }

}

