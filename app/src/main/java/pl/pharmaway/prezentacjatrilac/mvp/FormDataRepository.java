package pl.pharmaway.prezentacjatrilac.mvp;

import java.util.List;

public interface FormDataRepository {
    List<Form> getNotSendForms();
    void markAsSend(Form form);
    boolean hasNotSendForms();
    void storeNotSendForm(Form form);
}
