package sample.Exceptions;

/**
 * Created by maxhe on 22-6-2017.
 */
public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException(){
        super("The two password fields didn't match!");
    }
}

