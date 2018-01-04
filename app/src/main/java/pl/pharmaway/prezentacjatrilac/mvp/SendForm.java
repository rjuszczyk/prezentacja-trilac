package pl.pharmaway.prezentacjatrilac.mvp;

public interface SendForm {
    Cancelable sendForm(Form form, Callback callback);

    interface Callback {
        void onSuccess();
        void onFailure();
    }
}
