package org.example.presentation.servlet;

import org.example.data.Produto;
import org.example.domain.service.*;
import org.example.presentation.DTO.CreateProdutoDTO;
import org.example.presentation.DTO.ResponseDTO;
import org.example.presentation.DTO.UpdateLativoDTO;
import org.example.presentation.DTO.UpdateProdutoDTO;
import org.example.presentation.mappers.CreateProdutoMapper;
import org.example.presentation.mappers.UpdateProdutoMapper;
import org.example.util.JsonConverter;
import org.example.util.UuidConverter;
import org.example.util.exception.ProductException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet("/produtos/*")
public class ProdutoController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // produto/ -> GET -> listar produtos
        // produto/hash -> GET -> buscar produto por hash
        // produto/id -> GET -> buscar produto por id
        // produto/ean13 -> GET -> buscar produto por ean13
        // produto/nome -> GET -> buscar produto por nome

        String parametro = request.getPathInfo().split("/").length > 1 ? request.getPathInfo().split("/")[1] : null;

        response.setContentType("application/json");
        if(parametro != null) {
            try {
                Produto produto = GetProdutoService.execute(parametro);
                ResponseDTO<Produto> responseDTO = new ResponseDTO<>(produto, "Produto encontrado com sucesso!", 200);
                response.setStatus(200);
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            } catch (ProductException e) {
                response.setStatus(e.getCode());
                ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, e.getMessage(), e.getCode());
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }
        }else {
            try {
                int page = request.getHeader("page") != null ? Integer.parseInt(request.getHeader("page")) : 1;
                int limit = request.getHeader("limit") != null ? Integer.parseInt(request.getHeader("limit")) : 10;
                ResponseDTO<ArrayList<Produto>> responseDTO = new ResponseDTO<>(ListProdutoService.execute(page, limit), "Produtos encontrados com sucesso!", 200);
                response.setStatus(200);
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            } catch (ProductException e) {
                response.setStatus(e.getCode());
                ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, e.getMessage(), e.getCode());
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }
        }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // produto/ -> POST -> cadastrar produto

        response.setContentType("application/json");
            String body = getBodyReqJson(request);

            try{
                Produto produto = CreateProdutoMapper.toProduto(JsonConverter.fromJson(body, CreateProdutoDTO.class));
                produto = CreateProdutoService.execute(produto);
                ResponseDTO<Produto> responseDTO = new ResponseDTO<>(produto, "Produto cadastrado com sucesso!", 200);
                response.setStatus(200);
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }catch (ProductException e){
                response.setStatus(e.getCode());
                ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, e.getMessage(), e.getCode());
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // produto/hash -> PUT -> atualizar produto
        response.setContentType("application/json");

        String hash = request.getPathInfo().split("/").length > 1 ? request.getPathInfo().split("/")[1] : null;

        if(hash == null || hash.isEmpty() || !UuidConverter.isValid(hash)) {
            response.setStatus(400);
            ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, "hash invalido!", 400);
            response.getWriter().println(JsonConverter.toJson(responseDTO));
        }
        
        String body = getBodyReqJson(request);

        String context = request.getPathInfo().split("/").length > 2 ? request.getPathInfo().split("/")[2] : null;


        if(context != null && context.equals("ativo")){
            try {
                Produto produto = UpdateLativoService.execute(UuidConverter.toUuid(hash), JsonConverter.fromJson(body, UpdateLativoDTO.class));
                ResponseDTO<Produto> responseDTO = new ResponseDTO<>(produto, "Produto ativado com sucesso!", 200);
                response.setStatus(200);
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            } catch (ProductException e) {
                response.setStatus(e.getCode());
                ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, e.getMessage(), e.getCode());
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }
        }else {
            try {
                Produto produto = UpdateProdutoMapper.toProduto(JsonConverter.fromJson(body, UpdateProdutoDTO.class), UuidConverter.toUuid(hash));
                produto = UpdateProdutoService.execute(produto);
                ResponseDTO<Produto> responseDTO = new ResponseDTO<>(produto, "Produto atualizado com sucesso!", 200);
                response.setStatus(200);
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            } catch (ProductException e) {
                response.setStatus(e.getCode());
                ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, e.getMessage(), e.getCode());
                response.getWriter().println(JsonConverter.toJson(responseDTO));
            }
        }
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
            ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, "Produto dasativado com sucesso!", 200);
            response.setStatus(200);
            response.getWriter().println(JsonConverter.toJson(responseDTO));
        } catch (ProductException e) {
            response.setStatus(e.getCode());
            ResponseDTO<Void> responseDTO = new ResponseDTO<>(null, e.getMessage(), e.getCode());
            response.getWriter().println(JsonConverter.toJson(responseDTO));
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
