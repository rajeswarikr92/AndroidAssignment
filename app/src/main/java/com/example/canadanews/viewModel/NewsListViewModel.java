package com.example.canadanews.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.canadanews.service.APIClient;
import com.example.canadanews.service.APIInterface;
import com.example.canadanews.service.model.NewsList;
import com.example.canadanews.service.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListViewModel extends AndroidViewModel {
    private MutableLiveData<ResponseModel> responseModelLiveData = new MutableLiveData<>();

    public MutableLiveData<ResponseModel> getResponseData() {
        return responseModelLiveData;
    }

    public NewsListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getNewsList() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final ResponseModel responseModel = new ResponseModel();
        apiInterface.getNews().enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                responseModel.setObject(response.body(), response.isSuccessful(), response.headers(), response.code());
                responseModelLiveData.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                responseModel.setThrowable(t);
                responseModelLiveData.setValue(responseModel);
            }
        });
    }
}
