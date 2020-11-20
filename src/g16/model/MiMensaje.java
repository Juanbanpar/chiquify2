package g16.model;

import java.io.Serializable;

public class MiMensaje implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String senderId;
    private String receiverId;
    private String message;

    public MiMensaje(String senderId, String receiverId, String message) {
        super();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    public MiMensaje() {
		super();
	}

	public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}