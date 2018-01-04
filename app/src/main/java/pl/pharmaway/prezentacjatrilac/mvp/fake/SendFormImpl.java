package pl.pharmaway.prezentacjatrilac.mvp.fake;

import android.os.Handler;

import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;
import pl.pharmaway.prezentacjatrilac.mvp.Form;
import pl.pharmaway.prezentacjatrilac.mvp.SendForm;

public class SendFormImpl implements SendForm {
    @Override
    public Cancelable sendForm(Form form, final Callback callback) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callback.onSuccess();
            }
        };
        handler.postDelayed(runnable, 2000);

        SimpleCancelable simpleCancelable = new SimpleCancelable(new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacks(runnable);
            }
        });

        return simpleCancelable;
    }
}
