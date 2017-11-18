package co.com.call.respuestas.dto;

/**
 * abstraccion de objeto para recibir el numero de telefono recibido en la
 * llamdas
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
