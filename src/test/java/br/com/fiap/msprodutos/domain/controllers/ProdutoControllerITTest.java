package br.com.fiap.msprodutos.domain.controllers;

import br.com.fiap.msprodutos.domain.dto.ProdutoDtoRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Profile("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerITTest {
    @LocalServerPort
    private int porta;

    @BeforeEach
    void setUp() {
        RestAssured.port = porta;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class buscarProdutos {
        @Test
        void deveListarProdutos() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                        .get("/produtos")
                    .then()
                        .statusCode(HttpStatus.SC_OK);
        }

        @Test
        void deveBuscarProdutoPorId() {
            given()
                    .pathParam("id", 1)
                    .when()
                        .get("/produtos/{id}")
                    .then()
                        .statusCode(HttpStatus.SC_OK);
        }

        @Test
        void naoDeveBuscarProdutoPorIdInexistente() {
            given()
                    .pathParam("id", 50000)
                    .when()
                    .get("/produtos/{id}")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .body("message", is("Produto não encontrado"));
        }
    }

    @Nested
    class cadastrarProdutos{
        @Test
        void deveCadastrarProduto() {
            given()
                    .contentType(ContentType.JSON)
                    .body(new ProdutoDtoRequest(
                            "Produto Teste",
                            "Descrição",
                            10,
                            100.0)
                    )
                    .when()
                        .post("/produtos")
                    .then()
                        .statusCode(HttpStatus.SC_CREATED)
            .body("nome", is("Produto Teste"));
        }

        @Test
        void naoDeveCadastrarProdutoExistente(){
            given()
                    .contentType(ContentType.JSON)
                    .body(new ProdutoDtoRequest(
                            "Produto 1",
                            "Descrição do produto 1",
                            10,
                            100.0)
                    )
                    .when()
                    .post("/produtos")
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("message", is("Já existe produto cadastrado com esse nome"));

        }
    }

}
