package RESTResources;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import RESTResources.model.LoginForm;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.utils.SecUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Path("/login")
public class RESTLogin {
	
	@POST
	@Path("/veridateToken")
	public static Jws<Claims> validateToken(String tokenString) {
		Claims claims = null;
		try{
		 		claims =
		Jwts.parser().setSigningKey(key)
		.parseClaimsJws(tokenString).getBody();
		} catch (ExpiredJwtException | UnsupportedJwtException |
		 MalformedJwtException | SignatureException
						| IllegalArgumentException e) {
					// Do something with those exceptions!
				
				}
				return (Jws<Claims>) claims;
					
			}

	private static Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);

	@POST
	@Path("/verify")
	public String getLogin(LoginForm data){
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		
		int id = data.getOprId();
		String password = data.getPassword();

		OperatorDTO oprDTO = null;
		try {
			oprDTO = oprDAO.getOperator(id);
		} catch (DALException e) {
			e.printStackTrace();
			return "Id does not exist.";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Invalid Id.";
		}
		
		long nowMillis = System.currentTimeMillis();
		long ttlMillis = 1000000; // 16.666666667min
	    long expMillis = nowMillis + ttlMillis;
		Date exp = new Date(expMillis);
		
		if (validate(password, oprDTO)) {
			return Jwts.builder()
					.setIssuer("Gruppe 11 web service")
					.claim("Id", oprDTO.getOprId())
					.claim("Name", oprDTO.getOprName())
					.claim("Ini", oprDTO.getOprIni())
					.setExpiration(exp)
					.signWith(SignatureAlgorithm.HS512, key)
					.compact();
		} else {
			throw new WebApplicationException(Status.FORBIDDEN);
		}
	}

	private boolean validate(String password, OperatorDTO oprDTO) {
		SecUtils secUtil = SecUtils.getInstance();
		
		if (secUtil.sha256(password).equals(oprDTO.getOprPassword())) {
			return true;
		} else {
			return false;
		}
	}


}
