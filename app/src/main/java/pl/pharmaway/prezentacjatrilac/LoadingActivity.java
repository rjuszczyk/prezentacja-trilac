package pl.pharmaway.prezentacjatrilac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.pharmaway.prezentacjatrilac.mvp.LoadingPresenter;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingView;
import pl.pharmaway.prezentacjatrilac.mvp.fake.FormDataRepositoryImpl;
import pl.pharmaway.prezentacjatrilac.mvp.fake.LoadingModelImpl;
import pl.pharmaway.prezentacjatrilac.mvp.fake.SendFormImpl;

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

        loadingPresenter = new LoadingPresenter(
                new LoadingModelImpl(),
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
