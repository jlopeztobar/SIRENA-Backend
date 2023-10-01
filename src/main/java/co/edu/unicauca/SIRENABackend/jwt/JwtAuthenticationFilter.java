package co.edu.unicauca.SIRENABackend.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Filtra las solicitudes para autenticar a los usuarios utilizando tokens JWT.
     *
     * @param request     El objeto HttpServletRequest.
     * @param response    El objeto HttpServletResponse.
     * @param filterChain La cadena de filtros para procesar la solicitud.
     * @throws ServletException Si hay un error en el servlet.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String token=getTokenFromRequest(request);
        final String username;

        if (token==null){
            filterChain.doFilter(request, response);
            return;
        }
        
        username=jwtService.getUsernameFromToken(token);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        
            if(jwtService.isTokenValid(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());           
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }
    
    /**
     * Obtiene el token de autorización de la solicitud HTTP.
     *
     * @param request La solicitud HTTP.
     * @return El token JWT si está presente en la solicitud, de lo contrario, null.
     */
    private String getTokenFromRequest(HttpServletRequest request){
        final String autheader=request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.isNotEmpty(autheader) && autheader.startsWith("Bearer "))
        {
            return autheader.substring(7);
        }
        return null;
    }
}
