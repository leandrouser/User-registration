package Servico;

import Interface.UsuarioInt;
import Repositorio.UsuarioRep;
import Entidades.Usuario;

import java.util.List;

public class UsuarioSer implements UsuarioInt {
    private UsuarioRep repositorio = new UsuarioRep();

    @Override
    public void criarUsuario(Usuario usuario) {
        if (usuario.getCpf() == null || !usuario.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
        repositorio.getUsuarios().add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = repositorio.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
        } else {
            System.out.println("\n--- Lista de Usuários Cadastrados ---");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return repositorio.getUsuarios().stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean atualizarUsuario(String cpf, Usuario usuarioAtualizado) {
        Usuario usuario = buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            return true;
        }
        return false;
    }

    @Override
    public boolean excluirUsuario(String cpf) {
        Usuario usuario = buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            repositorio.getUsuarios().remove(usuario);
            return true;
        }
        return false;
    }

    public boolean validarDataNascimento(String dataNascimento) {
        return dataNascimento.matches("\\d{2}/\\d{2}/\\d{4}");
    }
}
