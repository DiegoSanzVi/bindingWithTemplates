package demo;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the form-component.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("form-component")
@HtmlImport("src/form-component.html")
public class FormComponent extends PolymerTemplate<FormComponent.FormComponentModel> {

    /**
     * Creates a new FormComponent.
     */
    public FormComponent() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between FormComponent and form-component.html
     */
    public interface FormComponentModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
