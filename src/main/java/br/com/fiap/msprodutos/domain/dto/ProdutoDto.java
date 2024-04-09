package br.com.fiap.msprodutos.domain.dto;

import br.com.fiap.msprodutos.domain.entities.ProdutoEntity;
import br.com.fiap.msprodutos.domain.expections.BusinessException;

public record ProdutoDto(
        int id,
        String nome,
        String descricao,
        int quantidadeEstoque,
        double preco
) {
    public ProdutoEntity toEntity() throws BusinessException {
        return new ProdutoEntity(nome, descricao, quantidadeEstoque, preco);
    }
}