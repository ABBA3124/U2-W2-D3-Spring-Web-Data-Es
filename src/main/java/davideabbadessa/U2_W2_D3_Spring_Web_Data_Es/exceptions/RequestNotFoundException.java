package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.exceptions;

public class RequestNotFoundException  extends RuntimeException{
    public RequestNotFoundException(String messaggio){
        super(messaggio);
    }
}
