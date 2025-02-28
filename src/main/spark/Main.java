//package spark;
import static spark.Spark.*;

import java.util.*;
import model.Produto;
import dao.ProdutoDAO;
import spark.Request;
import spark.Response;
import spark.Route;

public class Main {

    public static void main(String[] args) {
        port(4567); // Porta do Spark

        // Rota para mostrar o formulário HTML
        get("/formProduto", (req, res) -> {
            res.type("text/html");
            return new java.nio.file.Files.readAllBytes(Paths.get("form.html")); // Caminho para o arquivo HTML
        });

        // Rota para adicionar produto no banco de dados
        post("/addProduto", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                int codigo = Integer.parseInt(request.queryParams("codigo"));
                String nome = request.queryParams("nome");
                double preco = Double.parseDouble(request.queryParams("preco"));

                Produto produto = new Produto(codigo, nome, preco);
                ProdutoDAO produtoDAO = new ProdutoDAO();

                if (produtoDAO.insert(produto)) {
                    response.redirect("/produtoAdicionado");
                } else {
                    response.redirect("/erroAdicionar");
                }
                return null;
            }
        });

        // Rota para mostrar uma mensagem de sucesso
        get("/produtoAdicionado", (req, res) -> {
            return "Produto adicionado com sucesso!";
        });

        // Rota para mostrar mensagem de erro
        get("/erroAdicionar", (req, res) -> {
            return "Erro ao adicionar o produto.";
        });
    }
}
