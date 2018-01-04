package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class Page3 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View arrow1 = findViewById(R.id.chart1);
        View arrow2 = findViewById(R.id.chart2);
        View arrow3 = findViewById(R.id.chart3);

        if(savedInstanceState == null) {
            setInvisible(arrow1, arrow2, arrow3);
            animateIn(500, new Page6.AnimationOpeartor() {
                @Override
                public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
                    return animator.alpha(1);
                }
            }, arrow1, arrow2, arrow3);
        } else {
            setVisible(arrow1, arrow2, arrow3);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page3;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page4.class;
    }
}
