package com.example.canadanews.view.UI;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.canadanews.R;
import com.example.canadanews.models.NewsListItemModel;


import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ListViewHolder> {

    private ArrayList<NewsListItemModel> dataList;

    public NewsListAdapter( ArrayList<NewsListItemModel> dataList){
        this.dataList = dataList;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        TextView txtTitle, description;
        ImageView image;


        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            description = mView.findViewById(R.id.description);
            image = mView.findViewById(R.id.image);
        }
    }
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        if(dataList.get(i).getTitle() != null)
            listViewHolder.txtTitle.setText(dataList.get(i).getTitle());
        else
            listViewHolder.txtTitle.setText(R.string.title_not_available);

        if(dataList.get(i).getDescription() != null)
            listViewHolder.description.setText(dataList.get(i).getDescription());
        else
            listViewHolder.description.setText(R.string.description_not_available);


        if(dataList.get(i).getImageUrl() != null) {
            String image = dataList.get(i).getImageUrl();
            Glide.with(listViewHolder.itemView.getContext())
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(listViewHolder.image);
        }
        else {
            Glide.with(listViewHolder.itemView.getContext())
                    .load(R.drawable.placeholder)
                    .into(listViewHolder.image);
        }

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


}



