package dao;

import java.sql.*;
import java.security.*;
import java.math.*;

public class DAO {
    protected Connection conexao;
    
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Filho090908";

    public DAO() {
        this.conectar();
    }

    public final void conectar() {
        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
        }
    }

    public boolean close() {
        boolean status = false;
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                status = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public static String toMD5(String senha) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
