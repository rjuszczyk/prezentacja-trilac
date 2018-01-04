package pl.pharmaway.prezentacjatrilac.mvp.fake;

import java.util.ArrayList;
import java.util.List;

import pl.pharmaway.prezentacjatrilac.mvp.Form;
import pl.pharmaway.prezentacjatrilac.mvp.FormDataRepository;

public class FormDataRepositoryImpl implements FormDataRepository {
    @Override
    public List<Form> getNotSendForms() {
        List<Form> forms = new ArrayList<>();
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        return forms;
    }

    @Override
    public void markAsSend(Form form) {

    }

    @Override
    public boolean hasNotSendForms() {
        return true;
    }

    @Override
    public void storeNotSendForm(Form form) {

    }
}
