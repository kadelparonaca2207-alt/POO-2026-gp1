package pe.edu.upeu.final_project_gp1.modelo;

public class UserSession {
    private static UserSession instance;
    private Usuario userLogger;

    public UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public Usuario getUserLogger() {
        return userLogger;
    }

    public void setUserLogger(Usuario usuario){
        this.userLogger = usuario;
    }

    public void cleanSession(){
        userLogger = null;
    }

}
