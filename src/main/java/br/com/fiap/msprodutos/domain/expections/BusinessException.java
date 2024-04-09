package br.com.fiap.msprodutos.domain.expections;

public class BusinessException extends Exception{
    public BusinessException(final String message){
        super(message);
    }
}
