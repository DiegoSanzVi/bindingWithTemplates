package com.vaadin.flow.tutorial.binder.ui;

import java.util.Optional;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.tutorial.binder.data.User;

@Tag("user-form")
@HtmlImport("src/user-form.html")
public class UserForm extends PolymerTemplate<UserForm.FormComponentModel> {

    @Id("email")
    private TextField email;

    @Id("first-name")
    private TextField firstName;

    @Id("last-name")
    private TextField lastName;

    @Id("comments")
    private TextArea comment;

    @Id("action-buttons")
    private FormButtonsBar actionButtons;


    private Binder<User> binder;

    private Method saveMethod;
    private Method deleteMethod;

    /**
     * Creates a new FormComponent.
     */
    public UserForm() {
        initBinder();
        initListeners();
    }

    private void initBinder() {
        binder = new Binder<>();

        // email
        binder.forField(email).withValidator(
                new EmailValidator(    "This doesn't look like a valid email address")
        ).bind(User::getEmail, User::setEmail);

        // firstName
        binder.forField(firstName).withValidator( firstName -> firstName.length() > 1,
                "The first name must contains at least 2 characters").asRequired()
                .bind(User::getFirstName, User::setFirstName);

        // lastName
        binder.forField(lastName).asRequired("It can not be empty")
                .bind(User::getLastName, User::setLastName);

        // comment
        binder.forField(comment).asRequired("It can not be empty")
                .bind(User::getComment, User::setComment);
    }

    public void setBean(User userComment){
        binder.setBean(userComment);
    }

    public void removeBean(){
        binder.removeBean();
    }

    public void setEnabled(boolean enabled){
        email.setEnabled(enabled);
        firstName.setEnabled(enabled);
        lastName.setEnabled(enabled);
        comment.setEnabled(enabled);

        actionButtons.setCancelDisabled(!enabled);
        actionButtons.setSaveDisabled(!enabled);
        actionButtons.setDeleteDisabled(!enabled);
    }

    public FormButtonsBar getActionButtons() {
        return actionButtons;
    }

    public Optional<User> getBean(){
        return Optional.of(binder.getBean());
    }

    public Binder<User> getBinder() {
        return binder;
    }

    public void initListeners() {

        actionButtons.addSaveListener(saveEvent -> {
            if ( binder.validate().isOk()){
                if (saveMethod != null) {
                    saveMethod.execute();
                }
            }
        });

        actionButtons.addCancelListener(cancelEvent -> {
            binder.removeBean();
        });

        actionButtons.addDeleteListener(deleteEvent -> {
            if (binder.validate().isOk()) {

                binder.removeBean();

                if (deleteMethod != null) {
                    deleteMethod.execute();
                }
            }
        });

    }

    public void addSaveListener(Method method) {
        saveMethod = method;
    }

    public void addDeleteListener(Method method) {
        deleteMethod = method;
    }


    /**
     * This model binds properties between FormComponent and user-form.html
     */
    public interface FormComponentModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

    @FunctionalInterface
    public interface Method {
        public void execute();
    }
}
