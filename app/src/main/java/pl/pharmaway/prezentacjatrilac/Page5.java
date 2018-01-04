package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class Page5 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View page5_1 = findViewById(R.id.page5_1);
        View page5_2 = findViewById(R.id.page5_2);
        View page5_3 = findViewById(R.id.page5_3);
        View page5_4 = findViewById(R.id.page5_4);
        View page5_5 = findViewById(R.id.page5_5);
        View page5_6 = findViewById(R.id.page5_6);
        View page5_7 = findViewById(R.id.page5_7);

        if(savedInstanceState == null) {
            setVisible(page5_1, page5_2, page5_3, page5_4, page5_5, page5_6, page5_7);
            animateIn(500, new Page6.AnimationOpeartor() {
                @Override
                public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
                    return animator.alpha(0);
                }
            }, page5_1, page5_2, page5_3, page5_4, page5_5, page5_6, page5_7);
        } else {
            setInvisible(page5_1, page5_2, page5_3, page5_4, page5_5, page5_6, page5_7);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page5;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page6.class;
    }
}
