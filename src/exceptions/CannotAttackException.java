package exceptions;

@SuppressWarnings("serial")
public class CannotAttackException extends HearthstoneException {
	
	public CannotAttackException(){
		super();
	}
	
	public CannotAttackException(String s){
		super(s);
	}
}
