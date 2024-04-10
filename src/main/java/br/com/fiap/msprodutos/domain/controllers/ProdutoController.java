package br.com.fiap.msprodutos.domain.controllers;

import br.com.fiap.msprodutos.domain.dto.ProdutoDtoRequest;
import br.com.fiap.msprodutos.domain.expections.BusinessException;
import br.com.fiap.msprodutos.domain.service.ProdutoService;
import br.com.fiap.msprodutos.infra.MessageErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> listarProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarProdutoPorId(id));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoDtoRequest produto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(produto));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDtoRequest produto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, produto));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable int id) {
        try {
            produtoService.excluirProduto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PutMapping("/{id}/estoque/acrescentar/{quantidade}")
    public ResponseEntity<?> acrescentarEstoque(@PathVariable Integer id, @PathVariable int quantidade) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.acrescentarEstoque(id, quantidade));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }

    @PutMapping("/{id}/estoque/diminuir/{quantidade}")
    public ResponseEntity<?> diminuirEstoque(@PathVariable Integer id, @PathVariable int quantidade) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.diminuirEstoque(id, quantidade));
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageErrorHandler.create(e.getMessage()));
        }
    }
}