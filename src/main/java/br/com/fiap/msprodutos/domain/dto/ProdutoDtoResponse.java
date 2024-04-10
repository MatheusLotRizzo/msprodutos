package br.com.fiap.msprodutos.domain.dto;

import br.com.fiap.msprodutos.domain.entities.ProdutoEntity;
import br.com.fiap.msprodutos.domain.expections.BusinessException;

public record ProdutoDtoResponse(
        int id,
        String nome,
        String descricao,
        int quantidadeEstoque,
        double preco
) {
}