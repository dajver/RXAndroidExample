package com.project.dajver.rxandroidecample.api;

import com.project.dajver.rxandroidecample.api.model.ArticlesModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by gleb on 11/14/17.
 */

public class RepositoryImpl implements IRepository {

    @Override
    public Observable<ArrayList<ArticlesModel>> getArticles(String urlLink) {
        return Observable.create(observableEmitter -> {
            ArrayList<ArticlesModel> articlesModels = new ArrayList<>();
            Document doc;
            try {
                doc = Jsoup.connect(urlLink).get();

                Elements titleElement = doc.getElementsByClass("title");
                Elements imageElement = doc.getElementsByClass("cover-image");

                for(int i = 0; i < titleElement.size(); i++) {
                    Elements imgsrc = imageElement.get(i).select("img");
                    String style = imgsrc.attr("src");

                    Elements ahref = titleElement.get(i).select("a");
                    String titleText = ahref.text();

                    ArticlesModel model = new ArticlesModel();
                    model.setName(titleText);
                    model.setImageUrl(style);

                    articlesModels.add(model);
                }
                observableEmitter.onNext(articlesModels);
            } catch (IOException e) {
                observableEmitter.onError(e);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }
}
