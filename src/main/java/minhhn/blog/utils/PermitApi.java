package minhhn.blog.utils;

import minhhn.blog.dto.ApiDto;
import org.springframework.http.HttpMethod;

import java.util.List;

public class PermitApi {

  public static final List<ApiDto> PERMIT_APIS;

  private PermitApi() {

  }

  static {
    PERMIT_APIS = List.of(
        new ApiDto("/api/authenticate", HttpMethod.POST),
        new ApiDto("/api/categories/with-sub", HttpMethod.GET),
        new ApiDto("/api/posts", HttpMethod.GET),
        new ApiDto("/api/posts/*", HttpMethod.GET),
        new ApiDto("/api/users/register", HttpMethod.POST)
    );
  }

}
