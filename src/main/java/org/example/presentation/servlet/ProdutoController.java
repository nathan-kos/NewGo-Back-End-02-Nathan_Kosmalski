package org.example.presentation.servlet;

import com.google.gson.Gson;
import org.example.data.Produto;
import org.example.domain.service.CreateProdutoService;
import org.example.domain.service.DeleteProdutoService;
import org.example.presentation.DTO.CreateProdutoDTO;
import org.example.presentation.DTO.ResponseDTO;
import org.example.presentation.mappers.CreateProdutoMapper;
import org.example.util.JsonConverter;
import org.example.util.LocalizadorDeServico;
import org.example.util.exception.ProductExcption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

        response.setContentType("application/json");
            String body = getBodyReqJson(request);

            try{
                Produto produto = CreateProdutoMapper.toProduto(JsonConverter.fromJson(body, CreateProdutoDTO.class));
                produto = CreateProdutoService.execute(produto);
                ResponseDTO<Produto> responseDTO = new ResponseDTO<Produto>(produto, "Produto cadastrado com sucesso!", 200);
                response.setStatus(200);
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }catch (ProductExcption e){
                response.setStatus(e.getCode());
                ResponseDTO<Void> responseDTO = new ResponseDTO<Void>(null, e.getMessage(), e.getCode());
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }
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
        try {
            DeleteProdutoService.execute(hash);
            ResponseDTO<Void> responseDTO = new ResponseDTO<Void>(null, "Produto excluído com sucesso!", 200);
            response.setStatus(200);
            response.getWriter().println(JsonConverter.toJson(responseDTO));
        } catch (ProductExcption e) {
            response.setStatus(e.getCode());
            ResponseDTO<Void> responseDTO = new ResponseDTO<Void>(null, e.getMessage(), e.getCode());
            response.getWriter().println(gson.toJson(responseDTO));
        }
    }

    private static String getBodyReqJson(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return new String(requestBody.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
