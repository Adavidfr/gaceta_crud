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
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class OfficialBean implements Serializable {

    @Inject
    private OfficialService service;

    private List<Official> list;          
    private Official form;

    private Integer numeroInicial;     
    private Integer numeroActual;        

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        form = new Official();

        numeroInicial = service.findLastTabloidNumber() + 1;
    }

    public void nuevo() {
        form = new Official();
    }

    public void editar(Official o) {
        form = o;
    }

    public void guardar() {

        if (!list.contains(form)) {
            list.add(form);
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Agregado",
                        "Registro agregado a la gaceta"));

        form = new Official();
    }

    public void eliminar(Official o) {
        list.remove(o);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Eliminado",
                        "Registro eliminado de la lista"));
    }


    public void enviarGaceta() {

        if (list.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Advertencia",
                            "No hay registros para enviar"));
            return;
        }

        numeroActual = numeroInicial;

        for (Official o : list) {
            o.setTabloid_number(String.valueOf(numeroActual));
            service.create(o);
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Enviado",
                        "Gaceta No " + numeroActual + " enviada con éxito"));

        // limpiar tabla para nueva serie
        list = new ArrayList<>();
        numeroInicial = numeroActual + 1;
    }

    // GETTERS & SETTERS

    public List<Official> getList() {
        return list;
    }

    public Official getForm() {
        return form;
    }

    public void setForm(Official form) {
        this.form = form;
    }

    public Integer getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(Integer numeroInicial) {
        this.numeroInicial = numeroInicial;
    }
}