package Interface;

import Entidades.Usuario;
import java.util.List;

public interface UsuarioInt {
    void criarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorCpf(String cpf);
    boolean atualizarUsuario(String cpf, Usuario usuarioAtualizado);
    boolean excluirUsuario(String cpf);
}
