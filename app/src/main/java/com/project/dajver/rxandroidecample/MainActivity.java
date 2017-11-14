package com.project.dajver.rxandroidecample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.dajver.rxandroidecample.adapter.ArticlesRecyclerAdapter;
import com.project.dajver.rxandroidecample.adapter.manager.WrapperLinearLayout;
import com.project.dajver.rxandroidecample.api.RepositoryImpl;
import com.project.dajver.rxandroidecample.api.model.ArticlesModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new WrapperLinearLayout(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        new RepositoryImpl().getArticles("https://www.instructables.com/technology/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ArticlesModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ArrayList<ArticlesModel> articlesModels) {
                        ArticlesRecyclerAdapter articlesRecyclerAdapter = new ArticlesRecyclerAdapter(MainActivity.this, articlesModels);
                        recyclerView.setAdapter(articlesRecyclerAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() { }
                });

    }
}
