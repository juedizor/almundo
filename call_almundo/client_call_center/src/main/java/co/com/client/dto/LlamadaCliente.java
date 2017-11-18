package co.com.client.dto;

/**
 * abstraccion de objeto para simular el numero telefonico desde el cual se
 * realiza la llamada
 *
 * @author julio
 */
public class LlamadaCliente {

    private String numeroTelefono;

    public LlamadaCliente() {

    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

}
