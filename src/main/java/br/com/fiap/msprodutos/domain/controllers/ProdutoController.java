package br.com.fiap.msprodutos.domain.controllers;

import br.com.fiap.msprodutos.domain.dto.ProdutoDtoRequest;
import br.com.fiap.msprodutos.domain.exceptions.BusinessException;
import br.com.fiap.msprodutos.domain.service.ProdutoService;
import br.com.fiap.msprodutos.infra.handler.MessageErrorHandler;
import br.com.fiap.msprodutos.infra.swagger.annotations.ApiResponseSwaggerCreate;
import br.com.fiap.msprodutos.infra.swagger.annotations.ApiResponseSwaggerNoContent;
import br.com.fiap.msprodutos.infra.swagger.annotations.ApiResponseSwaggerOk;
import br.com.fiap.msprodutos.infra.swagger.annotations.responses.ApiResponseNotFoundJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Produtos", description = "Rotas para gerenciamento dos produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    @ApiResponseSwaggerOk
    public ResponseEntity<?> listarProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    @ApiResponseSwaggerOk
    @ApiResponseSwaggerNoContent
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarProdutoPorId(id));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo produto")
    @ApiResponseSwaggerCreate
    @ApiResponseNotFoundJson
    public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoDtoRequest produto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(produto));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto")
    @ApiResponseSwaggerOk
    @ApiResponseNotFoundJson
    public ResponseEntity<?> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDtoRequest produto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, produto));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto")
    @ApiResponseSwaggerNoContent
    @ApiResponseNotFoundJson
    public ResponseEntity<?> excluirProduto(@PathVariable int id) {
        try {
            produtoService.excluirProduto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PutMapping("/{id}/estoque/acrescentar/{quantidade}")
    @Operation(summary = "Acrescentar quantidade ao estoque")
    @ApiResponseSwaggerOk
    @ApiResponseNotFoundJson
    public ResponseEntity<?> acrescentarEstoque(@PathVariable Integer id, @PathVariable int quantidade) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.acrescentarEstoque(id, quantidade));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PutMapping("/{id}/estoque/diminuir/{quantidade}")
    @Operation(summary = "Diminuir quantidade do estoque")
    @ApiResponseSwaggerOk
    @ApiResponseNotFoundJson
    public ResponseEntity<?> diminuirEstoque(@PathVariable Integer id, @PathVariable int quantidade) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.diminuirEstoque(id, quantidade));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }
}