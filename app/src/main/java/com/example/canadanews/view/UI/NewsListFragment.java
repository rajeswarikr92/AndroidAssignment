package com.example.canadanews.view.UI;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.canadanews.R;
import com.example.canadanews.models.NewsListItemModel;

import java.util.ArrayList;

import static com.example.canadanews.view.UI.MainActivity.KEY_DATA;

public class NewsListFragment extends Fragment {
    private RecyclerView recyclerView;
    public SwipeRefreshLayout swipeRefreshLayout;
    private NewsListAdapter newsListAdapter;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        initView(view);
        ArrayList<NewsListItemModel> newsItems = getArguments().getParcelableArrayList(KEY_DATA);
        newsListAdapter =new NewsListAdapter( newsItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsListAdapter);
        newsListAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                ((MainActivity)getActivity()).pullToRefresh(true);
            }
        });
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recyclerview);
        swipeRefreshLayout= view.findViewById(R.id.swipeRefreshLayout);
    }
}
