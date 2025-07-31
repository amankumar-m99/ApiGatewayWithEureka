package indeedcoder.apigatewaywitheureka.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.handler.codec.http.HttpMethod;

@Configuration
public class RouterConfiguration {

	@Bean
	public RouteLocator configureRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("Service1", r1 -> r1.path("/service1/**").and().method(HttpMethod.GET.toString())
//						.and().cookie("secured", "Y*.")
//						.and().header("Authorization")
//						.and().host("http://localhost:4200/")
				.filters(f -> f.addRequestHeader("Authorization", "Basic Json.Web.Token.fromBean")
						.addResponseHeader("custom-resp", "custom-resp-value"))
				.uri("lb://ApiGatewayWithEurekaMS1"))
				.route("Service2",
						r1 -> r1.path("/service2/**").and().method(HttpMethod.GET.toString())
								.filters(f -> f.addRequestHeader("Authorization", "Basic Json.Web.Token.fromBean")
										.addResponseHeader("custom-resp", "custom-resp-value"))
								.uri("lb://ApiGatewayWithEurekaMS2"))
				.build();
	}

}
