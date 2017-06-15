package com.kshrd.articlecms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kshrd.articlecms.entity.ArticleResponse;
import com.kshrd.articlecms.webservice.ArticleService;
import com.kshrd.articlecms.webservice.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;

    ArticleAdapter articleAdapter;
    @BindView(R.id.btnfindname)
    Button btnfindname;

    @BindView(R.id.etfindname)
    EditText etfindname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRecyclerView();


        ArticleService articleService = ServiceGenerator.createService(ArticleService.class);
        final Call<ArticleResponse> call = articleService.findArticles(3);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse articleResponse = response.body();
                articleAdapter.addMoreItems(articleResponse.getArticlelist());

            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        btnfindname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ArticleService articleService1=ServiceGenerator.createService(ArticleService.class);
                Call<ArticleResponse> call1=articleService1.findArticlesbyname(String.valueOf(etfindname.getText()));
                call1.enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        ArticleResponse articleResponse1=response.body();
                        articleAdapter.clearItems();
                        articleAdapter.notifyDataSetChanged();
                        articleAdapter.addMoreItems(articleResponse1.getArticlelist());
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {

                    }
                });

            }
        });
        // Code..



    }

    private void setRecyclerView() {
        articleAdapter = new ArticleAdapter();
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        rvArticle.setAdapter(articleAdapter);
    }
}
