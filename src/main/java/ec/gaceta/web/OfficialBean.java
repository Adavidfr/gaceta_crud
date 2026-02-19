package ec.gaceta.web;

import ec.gaceta.model.Official;
import ec.gaceta.service.OfficialService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class OfficialBean implements Serializable {

    @Inject
    private OfficialService service;

    private List<Official> list;
    private Official form;

    @PostConstruct
    public void init() {
        list = service.findAll();
        form = new Official();
    }

    public void nuevo() {
        form = new Official();
    }

    public void editar(Official o) {
        form = o;
    }

    public void guardar() {

        if (form.getId() == null) {
            service.create(form);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Guardado",
                            "Registro " + form.getApplicationNumber() + " creado correctamente"));

        } else {
            service.update(form);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Actualizado",
                            "Registro " + form.getApplicationNumber() + " actualizado correctamente"));
        }

        list = service.findAll();
        form = new Official();
    }

    public void eliminar(Official o) {

        String tramite = o.getApplicationNumber(); // guardamos antes de eliminar

        service.delete(o.getId());
        list = service.findAll();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Eliminado",
                        "Registro " + tramite + " eliminado correctamente"));
    }

    public List<Official> getList() {
        return list;
    }

    public Official getForm() {
        return form;
    }

    public void setForm(Official form) {
        this.form = form;
    }
}
