package br.com.fiap.msprodutos.domain.service;

import br.com.fiap.msprodutos.domain.dto.ProdutoDto;
import br.com.fiap.msprodutos.domain.entities.ProdutoEntity;
import br.com.fiap.msprodutos.domain.expections.BusinessException;
import br.com.fiap.msprodutos.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> listarProdutos(){
        return produtoRepository.findAll().stream().map(ProdutoEntity::toDto).toList();
    }

    public ProdutoDto buscarProdutoPorId(int id){
        ProdutoEntity produtoEntity = produtoRepository.findById(id).orElse(null);
        if(produtoEntity == null){
            return null;
        }
        return produtoEntity.toDto();
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produto) throws BusinessException {
        return produtoRepository.save(produto.toEntity()).toDto();
    }

    public ProdutoDto atualizarProduto(Integer id, ProdutoDto produtoDto) throws BusinessException {
        ProdutoEntity produtoExistente = produtoRepository.findById(id).orElse(null);

        if(produtoExistente == null){
            throw new BusinessException("Produto não encontrado");
        }

        ProdutoEntity produtoAtualizado = new ProdutoEntity(
                produtoExistente.getId(),
                produtoDto.nome(),
                produtoDto.descricao(),
                produtoDto.quantidadeEstoque(),
                produtoDto.preco()
        );
        return produtoRepository.save(produtoAtualizado).toDto();
    }

    public void ExcluirProduto(int id) throws BusinessException {
        ProdutoEntity produtoExistente = produtoRepository.findById(id).orElse(null);

        if(produtoExistente == null){
            throw new BusinessException("Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }
}