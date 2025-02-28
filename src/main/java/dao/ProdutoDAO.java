package dao;

import java.sql.*;
import model.Produto;

public class ProdutoDAO extends DAO {

    public ProdutoDAO() {
        super();
        conectar();
    }

    public boolean insert(Produto produto) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO produto (codigo, nome, preco) "
                    + "VALUES (" + produto.getCodigo() + ", '" + produto.getNome() + "', " + produto.getPreco() + ");";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}