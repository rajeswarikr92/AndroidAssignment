package com.example.canadanews.view.UI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.canadanews.R;
import com.example.canadanews.models.NewsListItemModel;
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
    public static String KEY_DATA = "KEY_DATA";
    private NewsListFragment newsListFragment;
    private Boolean refresh = false;
    private ProgressDialog progressDialog;



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
        if(!refresh)
            showProgress(true);
        if(newsListObserver == null){
            newsListObserver = new android.arch.lifecycle.Observer<ResponseModel>() {
                @Override
                public void onChanged(@Nullable ResponseModel responseModel) {
                    if(responseModel.isSuccess){
                        if(!refresh)
                            showProgress(false);
                        else
                            if(newsListFragment != null){
                                newsListFragment.swipeRefreshLayout.setRefreshing(false);
                            }
                            refresh = false;

                        if(responseModel.getObject() != null){
                            NewsList newsList = (NewsList) responseModel.getObject();
                            if(newsList != null){
                                ArrayList<NewsListItemModel> newsListItemModelList = new ArrayList<>();
                                for (NewsItem newsItem : newsList.newsItem) {
                                    if(newsItem.title != null || newsItem.description != null || newsItem.imageHref!= null)
                                        newsListItemModelList.add(new NewsListItemModel(newsItem.title, newsItem.description, newsItem.imageHref));
                                }
                                    prepareDataForTheList(newsList.title, newsListItemModelList);

                            }
                        }
                    }else{
                        showProgress(false);
                        if (responseModel.getThrowable() != null) {
                            Throwable e = responseModel.getThrowable();
                            if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof NoRouteToHostException) {
                                Toast.makeText(getApplicationContext(),R.string.error_msg,Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            };
            newsListViewModel.getResponseData().observe(MainActivity.this, newsListObserver);
        }

        newsListViewModel.getNewsList();

    }

    private void prepareDataForTheList(String title, ArrayList<NewsListItemModel> newsListItemModelList){
        toolbar.setTitle(title);
        //creating listFragment
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_DATA, newsListItemModelList);

        newsListFragment = new NewsListFragment();
        newsListFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.list_container, newsListFragment);
        fragmentTransaction.commit();
    }

    public void pullToRefresh(Boolean isRefresh){
        refresh = isRefresh;
        if(isRefresh)
            registerObservers();
    }

    private void showProgress(Boolean show){
        if (show) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.progress_msg));
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }
}
