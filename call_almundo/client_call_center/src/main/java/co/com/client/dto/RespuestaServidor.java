package co.com.client.dto;

/**
 * abstracion de un objeto con la respuesta del server hacia el client
 *
 * @author julio
 */
public class RespuestaServidor {

    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
