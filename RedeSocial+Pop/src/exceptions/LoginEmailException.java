package exceptions;

public class LoginEmailException extends LoginException{
	
	public LoginEmailException(String email){
		super(" Um usuarix com email " + email + " nao esta cadastradx.");
	}

}
