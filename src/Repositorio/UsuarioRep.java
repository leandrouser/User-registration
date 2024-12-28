package Repositorio;

import Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRep {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> getUsuarios(){
        return usuarios;
    }

}
