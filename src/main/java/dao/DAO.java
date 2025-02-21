package dao;

import java.sql.*;
import java.security.*;
import java.math.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	protected Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	 	private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco";
	    private static final String USER = "seu_usuario";
	    private static final String PASSWORD = "sua_senha";

	    public static Connection conectar() {
	        try {
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
	        }
	    }
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public static String toMD5(String senha) throws Exception {
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0, senha.length());
		return new BigInteger(1,m.digest()).toString(16);
	}
}