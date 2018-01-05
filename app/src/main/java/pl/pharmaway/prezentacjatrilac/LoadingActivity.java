package pl.pharmaway.prezentacjatrilac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingPresenter;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingView;
import pl.pharmaway.prezentacjatrilac.mvp.fake.FormDataRepositoryImpl;
import pl.pharmaway.prezentacjatrilac.mvp.fake.LoadingModelImpl;
import pl.pharmaway.prezentacjatrilac.mvp.fake.SendFormImpl;
import pl.pharmaway.prezentacjatrilac.network.PrezentacjaApi;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

public class LoadingActivity extends AppCompatActivity implements LoadingView{

    LoadingPresenter loadingPresenter;
    private TextView progressMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        SYSTEM_UI_FLAG_FULLSCREEN |
                        SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(R.layout.page0);

        progressMsg = findViewById(R.id.progressMsg);

        SharedPreferences sharedPreferences = getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PrezentacjaApi prezentacjaApi = retrofit.create(PrezentacjaApi.class);
        loadingPresenter = new LoadingPresenter(
                new LoadingModelImpl(database, prezentacjaApi, sharedPreferences),
                this,
                new SendFormImpl(),
                new FormDataRepositoryImpl()
        );
        loadingPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingPresenter.stop();
    }

    @Override
    public void showLoading(String message) {
        progressMsg.setText(message);
    }

    @Override
    public void goToNext() {
        Intent intent = new Intent(this, Page1.class);
        startActivity(intent);
    }
}
