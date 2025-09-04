package com.hrms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.hrms.api")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API 文档").version("1.0").description("API 描述"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)));
    }
}
/*
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Tag(name = "groupRoles", description = "the groupRoles API")
@RequestMapping(value = "/groupRoles", produces = { "application/json" }, method = RequestMethod.DELETE)
public interface GroupRolesApi {

    @Operation(
        summary = "删除角色",
        description = "Deletes a role.",
        tags = { "群组相关/群组角色管理" },
        security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse2001.class)))
    })
    ResponseEntity<InlineResponse2001> deleteRole(@RequestParam Long roleId);
}
解释
@Tag:

替代 @Api, 用于定义 API 的标签和描述。
@Operation:

替代 @ApiOperation, 用于描述操作的详细信息，如摘要、描述和安全要求。
@ApiResponses 和 @ApiResponse:

替代 @ApiResponses 和 @ApiResponse, 用于定义可能的响应及其状态码和描述。
@SecurityRequirement:

替代 @Authorization, 用于定义需要的安全要求。
@Content 和 @Schema:

用于指定响应的内容类型和数据模型。
通过这些迁移，你可以将旧的 Swagger 注解转换为 Springdoc OpenAPI 的注解，使文档生成与新版本的 OpenAPI 标准兼容。
 */

