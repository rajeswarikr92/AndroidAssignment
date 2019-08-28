package com.example.canadanews;

import com.example.canadanews.models.NewsListItemModel;
import com.example.canadanews.view.UI.NewsListAdapter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {
    private NewsListAdapter adapter;
    private ArrayList<NewsListItemModel> data;

    @Before
    public void setUp() {

    }

    @Test
    public void preparelistData() {

        adapter  = Mockito.spy(new NewsListAdapter(new ArrayList<NewsListItemModel>()));
        Assert.assertEquals(0,adapter.getItemCount() );


    }
}
