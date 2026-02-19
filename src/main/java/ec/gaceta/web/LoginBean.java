package ec.gaceta.web;

import ec.gaceta.security.LDAPService;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String usuario;
    private String password;

    private LDAPService ldapService = new LDAPService();

    public void login() throws IOException {

        boolean autenticado = ldapService.autenticar(usuario, password);

        if (autenticado) {

            // Guardar usuario en sesión
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("usuarioLogueado", usuario);

            // Redirigir
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("officials.xhtml");

        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Usuario o contraseña incorrectos"));
        }
    }

    public void logout() {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .invalidateSession();

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
