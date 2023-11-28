package metaint.replanet.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("metaint.replanet.rest"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("기부모금 서비스 API 명세서")
                .description("React - JWT 토큰 인증 방식의 Spring Data Jpa 기반 기부모금 서비스 API 명세서 ")
                .contact(new Contact("전승재", "https://github.com/wjs960",""))
                .contact(new Contact("김유빈", "https://github.com/peaknicc", ""))
                .contact(new Contact("박지영", "https://github.com/jiyeong08",""))
                .contact(new Contact("이영현", "https://github.com/babyybiss",""))
                .contact(new Contact("김민주", "https://github.com/vaguewords",""))
                .contact(new Contact("이효진", "https://github.com/Gray-Grazer","cleit8307@gmail.com"))
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-from-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
}
