package br.com.fiap.msprodutos.domain.exceptions;

public class BusinessException extends Exception{
    public BusinessException(final String message){
        super(message);
    }
}
