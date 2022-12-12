package com.ferreirabrunomarcelo.productapi.configurations;

import com.ferreirabrunomarcelo.productapi.handlers.ProductHandler;
import com.ferreirabrunomarcelo.productapi.dtos.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfiguration {
    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/product/",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = ProductHandler.class,
                            beanMethod = "getAllProducts",
                            operation = @Operation(
                                    operationId = "getAllProducts",
                                    tags = "products",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "A product list was retrieved successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = ProductDTO.class
                                                    ))
                                            )
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/product/{id}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = ProductHandler.class,
                            beanMethod = "getProduct",
                            operation = @Operation(
                                    operationId = "getProduct",
                                    tags = "products",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "The product was retrieved successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = ProductDTO.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "404", description = "product not found")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id")
                                    }
                            )
                    ),

                    @RouterOperation(
                            path = "/product/",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.POST,
                            beanClass = ProductHandler.class,
                            beanMethod = "createProduct",
                            operation = @Operation(
                                    operationId = "createProduct",
                                    tags = "products",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "201",
                                                    description = "The product was created successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = ProductDTO.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "400", description = "bad request")
                                    },
                                    requestBody = @RequestBody(
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDTO.class
                                            ))
                                    )
                            )

                    ),

                    @RouterOperation(
                            path = "/product/{id}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.PUT,
                            beanClass = ProductHandler.class,
                            beanMethod = "updateProduct",
                            operation = @Operation(
                                    operationId = "updateProduct",
                                    tags = "products",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "The product was updated successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = ProductDTO.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "404", description = "product not found"),
                                            @ApiResponse(responseCode = "400", description = "bad request")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id")
                                    },
                                    requestBody = @RequestBody(
                                            content = @Content(schema = @Schema(
                                                    implementation = ProductDTO.class
                                            ))
                                    )
                            )

                    ),
                    @RouterOperation(
                            path = "/product/{id}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.DELETE,
                            beanClass = ProductHandler.class,
                            beanMethod = "deleteProduct",
                            operation = @Operation(
                                    operationId = "deleteProduct",
                                    tags = "products",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "204",
                                                    description = "The product was deleted successfully",
                                                    content = @Content(schema = @Schema(
                                                            implementation = ProductDTO.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "404", description = "product not found")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> routes(ProductHandler productHandler) {
        return route()
                .GET("/product/{id}", productHandler::getProduct)
                .GET("/product/", productHandler::getAllProducts)
                .POST("/product/", productHandler::createProduct)
                .PUT("/product/{id}", productHandler::updateProduct)
                .DELETE("/product/{id}", productHandler::deleteProduct)
                .build();
    }

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}
