package app;

import java.util.*;

import dao.DAO;
import dao.UserDAO;
import model.Usuario;

public class aplic{
    public static void main(String[] args) {
        try {
            UserDAO usuarioDAO = new UserDAO();

            // Inserir usuário
            System.out.println("\n\n==== Inserir usuário === ");
            Usuario usuario = new Usuario(11, "pablo", "pablo", 'M');
            if (usuarioDAO.insert(usuario)) {
                System.out.println("Inserção com sucesso -> " + usuario);
            }

            System.out.println("\n\n==== Testando autenticação ===");
            System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "pablo"));

            System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
            List<Usuario> usuarios = usuarioDAO.getSexoMasculino();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }

            System.out.println("\n\n==== Atualizar senha (código " + usuario.getCodigo() + ") === ");
            usuario.setSenha(DAO.toMD5("novaSenha"));
            usuarioDAO.update(usuario);

            System.out.println("\n\n==== Testando autenticação com nova senha ===");
            System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", DAO.toMD5("novaSenha")));

            System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
            usuarios = usuarioDAO.getOrderByCodigo();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }

            System.out.println("\n\n==== Excluir usuário (código " + usuario.getCodigo() + ") === ");
            usuarioDAO.delete(usuario.getCodigo());

            System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
            usuarios = usuarioDAO.getOrderByLogin();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
