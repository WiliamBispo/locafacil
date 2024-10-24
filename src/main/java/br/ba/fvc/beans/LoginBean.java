package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Funcionario;
import br.ba.fvc.mapeamento.Login;
import br.ba.fvc.rn.FuncionarioRN;
import br.ba.fvc.util.PasswordUtil;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Login login;
    FuncionarioRN funcionarioRN;

    public LoginBean() {
        login = new Login();
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String authenticateUser() {

        funcionarioRN = new FuncionarioRN();

        Funcionario funcionarioExistente = funcionarioRN.consultarUsuario(login.getLogin());

        if (funcionarioExistente != null) {
            String hashedPassword = funcionarioExistente.getSenha();

            if (PasswordUtil.checkPassword(login.getSenha(), hashedPassword)) {

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("usuario", login);

                return "/telas/index?faces-redirect=true";
            }
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos!", "Erro"));

        return "/security/login?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/security/login?faces-redirect=true";
    }
    
}
