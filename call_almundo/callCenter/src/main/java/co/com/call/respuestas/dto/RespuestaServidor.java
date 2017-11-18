package co.com.call.respuestas.dto;

/**
 * abstraccion del mensaje enviado del server - cliente
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
