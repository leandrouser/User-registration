
import Entidades.Usuario;
import Servico.UsuarioSer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioSer usuarioSer = new UsuarioSer();

        int opcao;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Remover Usuário");
            System.out.println("4. Alterar Usuário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                System.out.print("Escolha uma opção: ");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarUsuario(scanner, usuarioSer);
                    break;

                case 2:
                    listarUsuarios(scanner, usuarioSer);
                    break;

                case 3:
                    removerUsuario(scanner, usuarioSer);
                    break;

                case 4:
                    alterarUsuario(scanner, usuarioSer);
                    break;

                case 5:
                    System.out.println("Saindo do programa. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha entre 1 e 5.");
            }

        } while (opcao != 5);

        scanner.close();
    }

    private static void criarUsuario(Scanner scanner, UsuarioSer usuarioSer) {

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        String cpf;
        while (true) {

            System.out.print("Digite o CPF (11 dígitos): ");
            cpf = scanner.nextLine();

            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. Ele deve conter exatamente 11 dígitos numéricos. Tente novamente.");
            }
        }

        String dataNascimento;
        while (true) {
            System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
            dataNascimento = scanner.nextLine();

            if (usuarioSer.validarDataNascimento(dataNascimento)) {
                break;
            } else {
                System.out.println("Data de nascimento inválida. Use o formato dd/MM/yyyy e insira uma data válida. Tente novamente.");
            }
        }

        try {
            usuarioSer.criarUsuario(new Usuario(nome, cpf, dataNascimento));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }

    }

    private static void listarUsuarios(Scanner scanner, UsuarioSer usuarioSer) {
        usuarioSer.listarUsuarios();

    }

    private static void removerUsuario(Scanner scanner, UsuarioSer usuarioSer) {
        System.out.print("Digite o CPF do usuário a ser removido: ");
        String cpfExcluir = scanner.nextLine();
        if (usuarioSer.excluirUsuario(cpfExcluir)) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void alterarUsuario(Scanner scanner, UsuarioSer usuarioSer) {
        String cpfAlterar;
        while (true) {
            System.out.print("Digite o CPF do usuário a ser alterado: ");
            cpfAlterar = scanner.nextLine();

            if (usuarioSer.buscarUsuarioPorCpf(cpfAlterar) != null) {
                System.out.print("Digite o novo nome: ");
                String novoNome = scanner.nextLine();



                String novaData;
                while (true) {
                    System.out.print("Digite a nova data de nascimento (dd/MM/yyyy): ");
                    novaData = scanner.nextLine();

                    // Valida o formato da data
                    if (usuarioSer.validarDataNascimento(novaData)) {
                        break; // Sai do loop se a data for válida
                    } else {
                        System.out.println("Data de nascimento inválida. Use o formato dd/MM/yyyy. Tente novamente.");
                    }
                }

                // Atualiza o usuário com os novos dados
                if (usuarioSer.atualizarUsuario(cpfAlterar, new Usuario(novoNome, cpfAlterar, novaData))) {
                    System.out.println("Usuário alterado com sucesso.");
                } else {
                    System.out.println("Erro ao alterar o usuário.");
                }
                break; // Sai do loop principal após a alteração
            } else {
                System.out.println("Usuário não encontrado com o CPF informado. Tente novamente.");
            }
        }

    }

}
