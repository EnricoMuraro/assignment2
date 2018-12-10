////////////////////////////////////////////////////////////////////
// Enrico Muraro 1143775
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class RestaurantBillException extends Exception {
    private String message;

    public RestaurantBillException(String message)
    {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
