package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class Page4 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View page4_1 = findViewById(R.id.page4_1);
        View page4_2 = findViewById(R.id.page4_2);
        View page4_3 = findViewById(R.id.page4_3);
        View page4_4 = findViewById(R.id.page4_4);

        if(savedInstanceState == null) {
            setInvisible(page4_1, page4_2, page4_3, page4_4);
            animateIn(500, new Page6.AnimationOpeartor() {
                @Override
                public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
                    return animator.alpha(1);
                }
            }, page4_1, page4_2, page4_3, page4_4);
        } else {
            setVisible(page4_1, page4_2, page4_3, page4_4);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page4;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page5.class;
    }
}
