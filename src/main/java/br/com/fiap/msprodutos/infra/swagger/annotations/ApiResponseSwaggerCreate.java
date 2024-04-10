package br.com.fiap.msprodutos.infra.swagger.annotations;

import br.com.fiap.msprodutos.infra.swagger.annotations.responses.ApiResponseBadRequestJson;
import br.com.fiap.msprodutos.infra.swagger.annotations.responses.ApiResponseCreateJson;
import br.com.fiap.msprodutos.infra.swagger.annotations.responses.ApiResponseNotFoundJson;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ METHOD })
@ApiResponseCreateJson
@ApiResponseNotFoundJson
@ApiResponseBadRequestJson
public @interface ApiResponseSwaggerCreate {}
