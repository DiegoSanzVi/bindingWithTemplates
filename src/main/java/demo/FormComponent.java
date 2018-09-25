package demo;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("form-component")
@HtmlImport("src/form-component.html")
public class FormComponent extends PolymerTemplate<FormComponent.FormComponentModel> {

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


    private Binder<UserComment> binder;

    private Method saveMethod;
    private Method deleteMethod;

    /**
     * Creates a new FormComponent.
     */
    public FormComponent() {
        initBinder();
        initListeners();
    }

    private void initBinder() {
        binder = new Binder<>();

        // email
        binder.forField(email).withValidator(
                new EmailValidator(    "This doesn't look like a valid email address")
        ).bind(UserComment::getEmail, UserComment::setEmail);

        // firstName
        binder.forField(firstName).withValidator( firstName -> firstName.length() > 1,
                "The first name must contains at least 2 characters").asRequired()
                .bind(UserComment::getFirstName, UserComment::setFirstName);

        // lastName
        binder.forField(lastName).asRequired("It can not be empty")
                .bind(UserComment::getLastName, UserComment::setLastName);

        // comment
        binder.forField(comment).asRequired("It can not be empty")
                .bind(UserComment::getComment, UserComment::setComment);
    }

    public void setUserComment(UserComment userCommnet) {
        binder.setBean(userCommnet);
    }

    public FormButtonsBar getActionButtons() {
        return actionButtons;
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
     * This model binds properties between FormComponent and form-component.html
     */
    public interface FormComponentModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

    @FunctionalInterface
    public interface Method {
        public void execute();
    }
}
