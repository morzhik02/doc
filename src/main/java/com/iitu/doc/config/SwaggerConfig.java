package com.iitu.doc.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("My API")
//                .description("Some description")
//                .version("1.0")
//                .build();
//    }
@Bean
public OpenAPI openAPI() {
    return new OpenAPI()
            .info(new Info().title("CitizenGuard service API")
                    .description("Izteleu Marzhan, Tolshina Victoria Diploma Work")
                    .version("v1.0.0").license(new License().name("Apache 2.0")
                            .url("http://springdoc.org")).contact(new Contact().name("Izteleu Marzhan")
                            .email("27488@iitu.edu.kz")));
}

}
