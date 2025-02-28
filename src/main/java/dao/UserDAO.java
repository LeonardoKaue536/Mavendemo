package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Usuario usuario) {
        boolean status = false;
        try {
            String sql = "INSERT INTO usuario (codigo, login, senha, sexo) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, usuario.getCodigo());
            st.setString(2, usuario.getLogin());
            st.setString(3, usuario.getSenha());
            st.setString(4, String.valueOf(usuario.getSexo()));
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            System.err.println("Erro ao inserir usuário: " + u.getMessage());
        }
        return status;
    }

    public Usuario get(int codigo) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM usuario WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, codigo);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> getOrderByCodigo() {
        return get("codigo");
    }

    public List<Usuario> getOrderByLogin() {
        return get("login");
    }

    public List<Usuario> getOrderBySexo() {
        return get("sexo");
    }

    private List<Usuario> get(String orderBy) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuario ORDER BY " + orderBy;
            PreparedStatement st = conexao.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0)));
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    public boolean update(Usuario usuario) {
        boolean status = false;
        try {
            String sql = "UPDATE usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, usuario.getLogin());
            st.setString(2, usuario.getSenha());
            st.setString(3, String.valueOf(usuario.getSexo()));
            st.setInt(4, usuario.getCodigo());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            System.err.println("Erro ao atualizar usuário: " + u.getMessage());
        }
        return status;
    }

    public boolean delete(int codigo) {
        boolean status = false;
        try {
            String sql = "DELETE FROM usuario WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, codigo);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            System.err.println("Erro ao deletar usuário: " + u.getMessage());
        }
        return status;
    }

    public boolean autenticar(String login, String senha) {
        boolean resp = false;
        try {
            String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, login);
            st.setString(2, senha);
            ResultSet rs = st.executeQuery();
            resp = rs.next();
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }

	public List<Usuario> getSexoMasculino() {
		List<Usuario> usuarios = new ArrayList<>();
	
		try {
			String sql = "SELECT * FROM usuario WHERE sexo = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, "M"); // Define 'M' como o valor do parâmetro de sexo
			ResultSet rs = st.executeQuery();
	
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0)));
			}
			st.close();
		} catch (SQLException e) {
			System.err.println("Erro ao buscar usuários do sexo masculino: " + e.getMessage());
		}
		return usuarios;
	}
}
