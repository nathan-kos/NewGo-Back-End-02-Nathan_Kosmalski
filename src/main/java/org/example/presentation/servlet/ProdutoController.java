package org.example.presentation.servlet;

import com.google.gson.Gson;
import org.example.domain.service.DeleteProdutoService;
import org.example.presentation.DTO.ResponseDTO;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.ProductExcption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/produtos/*")
public class ProdutoController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // produto/ -> GET -> listar produtos
        // produto/hash -> GET -> buscar produto por hash
        // produto/id -> GET -> buscar produto por id
        // produto/ean13 -> GET -> buscar produto por ean13
        // produto/nome -> GET -> buscar produto por nome
        // produto/ativo -> GET -> buscar produtos ativos

        System.out.println(request.getPathInfo());
        System.out.println(request.getRequestURI());
        System.out.println(request.getServletPath());
        System.out.println(request.getQueryString());
        System.out.println(request.getParameterNames().nextElement()+ "   aqui");



        response.setContentType("text/plain");
        response.getWriter().println("Servlet de produtos rodando!");

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // produto/ -> POST -> cadastrar produto


    }


    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // produto/hash -> PUT -> atualizar produto



    }


    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // produto/hash -> DELETE -> excluir produto

        String hash = request.getParameter("hash");

        if(hash == null) {
            response.setStatus(400);
            response.getWriter().println("Hash não informado!");
            return;
        }



        response.setContentType("application/json");
        Gson gson = new Gson();
        try {
            DeleteProdutoService.execute(hash);
            ResponseDTO<Void> responseDTO = new ResponseDTO<Void>(null, "Produto excluído com sucesso!", 200);
            response.setStatus(200);
            response.getWriter().println(gson.toJson(responseDTO));
        } catch (ProductExcption e) {
            response.setStatus(e.getCode());
            ResponseDTO<Void> responseDTO = new ResponseDTO<Void>(null, e.getMessage(), e.getCode());
            response.getWriter().println(gson.toJson(responseDTO));
        }

    }
}
