package com.example.canadanews.view.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.canadanews.R;
import com.example.canadanews.service.model.NewsItem;
import com.example.canadanews.service.model.NewsList;
import com.example.canadanews.service.model.ResponseModel;
import com.example.canadanews.viewModel.NewsListViewModel;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Observer newsListObserver;
    private NewsListViewModel newsListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        newsListViewModel = ViewModelProviders.of(MainActivity.this).get(NewsListViewModel.class);
        registerObservers();

    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private  void registerObservers(){
        if(newsListObserver == null){
            newsListObserver = new android.arch.lifecycle.Observer<ResponseModel>() {
                @Override
                public void onChanged(@Nullable ResponseModel responseModel) {
                    if(responseModel.isSuccess){
                        if(responseModel.getObject() != null){
                            NewsList newsList = (NewsList) responseModel.getObject();
                            if(newsList != null){
                                    prepareDataForTheList(newsList);

                            }
                        }
                    }else{
                        if (responseModel.getThrowable() != null) {
                            Throwable e = responseModel.getThrowable();
                            if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof NoRouteToHostException) {
                                //HandleNetworkError
                            }
                        }
                    }

                }
            };
            newsListViewModel.getResponseData().observe(MainActivity.this, newsListObserver);
        }

        newsListViewModel.getNewsList();

    }

    private void prepareDataForTheList(NewsList newsList){
        toolbar.setTitle(newsList.title);
        ArrayList<NewsItem> dataList = new ArrayList<>();
        for(NewsItem listItem : newsList.newsItem){
            if(listItem.title != null || listItem.description != null || listItem.imageHref != null)
                dataList.add(listItem);
        }


    }
}
