package pe.com.rhsistemas.mf.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTokenState {

    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;
    
    @JsonProperty("id")
    private int idUsuario;
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    
    public UserTokenState() {
	}
    
    public UserTokenState(String accessToken, Long expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the expiresIn
	 */
	public Long getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
}