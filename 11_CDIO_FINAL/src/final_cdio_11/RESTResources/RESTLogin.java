package final_cdio_11.RESTResources;

import java.security.Key;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import final_cdio_11.RESTResources.model.LoginForm;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;
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

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();

	@POST
	@Path("/verifyToken")
	public static Jws<Claims> validateToken(String tokenString) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(key).parseClaimsJws(tokenString).getBody();
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {

		}
		return (Jws<Claims>) claims;

	}

	private static Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);

	@POST
	@Path("/verify")
	public String getLogin(LoginForm data) {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		int id = data.getOprId();
		String password = data.getPassword();

		OperatorDTO oprDTO = null;
		try {
			oprDTO = oprDAO.getOperator(id);
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errIdDoesNotExist;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return textHandler.errIdInvalid;
		}

		long nowMillis = System.currentTimeMillis();
		long ttlMillis = 1000000; // 16.666666667min
		long expMillis = nowMillis + ttlMillis;
		Date exp = new Date(expMillis);

		if (validate(password, oprDTO)) {
			return Jwts.builder().setIssuer("Gruppe 11 web service").claim("Id", oprDTO.getOprId()).claim("Name", oprDTO.getOprName()).claim("Ini", oprDTO.getOprIni()).setExpiration(exp).signWith(SignatureAlgorithm.HS512, key).compact();
		} else {
			throw new WebApplicationException(Status.FORBIDDEN);
		}
	}

	private boolean validate(String password, OperatorDTO oprDTO) {
		Utils secUtil = Utils.getInstance();

		if (secUtil.sha256(password).equals(oprDTO.getOprPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
