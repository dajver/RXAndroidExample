package com.project.dajver.rxandroidecample.api;

import com.project.dajver.rxandroidecample.api.model.ArticlesModel;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by gleb on 11/14/17.
 */

public interface IRepository {
    Observable<ArrayList<ArticlesModel>> getArticles(String url);
}
