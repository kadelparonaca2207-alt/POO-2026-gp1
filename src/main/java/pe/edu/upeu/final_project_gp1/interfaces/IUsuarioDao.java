package pe.edu.upeu.final_project_gp1.interfaces;

import pe.edu.upeu.final_project_gp1.modelo.Usuario;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

public interface IUsuarioDao {

    public Usuario login(String user, String password);

    public boolean insertUser(Usuario usuario);

}
