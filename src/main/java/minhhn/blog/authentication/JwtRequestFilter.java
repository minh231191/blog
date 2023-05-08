package minhhn.blog.authentication;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import minhhn.blog.exception.JwtTokenException;
import minhhn.blog.utils.PermittedApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;
  private final UserDetailsService jwtUserService;

  public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService jwtUserService) {
    this.jwtUserService = jwtUserService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");
    String username;
    String jwtToken = StringUtils.EMPTY;
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        throw new JwtTokenException("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        throw new JwtTokenException("JWT Token has expired");
      }
    } else {
      logger.error("Invalid token: " + jwtToken);
      throw new JwtTokenException("JWT Token does not begin with Bearer String");
    }
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = this.jwtUserService.loadUserByUsername(username);

      // if token is valid configure Spring Security to manually set
      // authentication
      if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
      }
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    boolean isNotNeededNoBeFiltered = PermittedApi.PERMIT_APIS.stream()
        .anyMatch(p -> new AntPathMatcher().match(p.getPath(), request.getServletPath()) && p.getMethod().matches(request.getMethod()));
    logger.info(request.getServletPath() + ", needed to be filtered: " + !isNotNeededNoBeFiltered);
    return isNotNeededNoBeFiltered;
  }

}
