package com.kshrd.articlecms.webservice;

import com.kshrd.articlecms.entity.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pirang on 6/14/17.
 */

public interface ArticleService {

    @GET("v1/api/articles")
    Call<ArticleResponse> findArticles(@Query("limit") int limit);

    @GET("v1/api/articles")
    Call<ArticleResponse> findArticlesbyname(@Query("title") String title);

}
