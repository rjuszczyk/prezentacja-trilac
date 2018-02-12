package pl.pharmaway.prezentacjatrilac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class Page6 extends FooterActivity {

    private boolean goBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBackToMenu = getIntent().getBooleanExtra("goBackToMenu", false);

        View arrow1 = findViewById(R.id.arrow1);
        View arrow2 = findViewById(R.id.arrow2);
        View arrow3 = findViewById(R.id.arrow3);

        View paragraph1 = findViewById(R.id.paragraph1);
        View paragraph2 = findViewById(R.id.paragraph2);
        View paragraph3 = findViewById(R.id.paragraph3);
        View paragraph4 = findViewById(R.id.paragraph4);

        if(savedInstanceState == null) {
            setInvisible(arrow1, arrow2, arrow3);
            animateIn(6500, new AnimationOpeartor() {
                @Override
                public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
                    return animator.alpha(0);
                }
            }, paragraph1, paragraph2, paragraph3, paragraph4);

            animateIn(500, new AnimationOpeartor() {
                @Override
                public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
                    return animator.alpha(1);
                }
            }, arrow1, arrow2, arrow3);
        } else {
            setInvisible(paragraph1, paragraph2, paragraph3, paragraph4);
            setVisible(arrow1, arrow2, arrow3);
        }
    }

    @Override
    public void onBackPressed() {
        if(!goBackToMenu) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, Page2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page6;
    }

    @Override
    protected Class<?> getNextActivity() {
        return FormActivity.class;
    }

    interface AnimationOpeartor {
        ViewPropertyAnimator apply(ViewPropertyAnimator animator);
    }
}
