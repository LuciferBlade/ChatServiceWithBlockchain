package chatservicewithblockchain;

/**
 *
 * @author blade
 */
import java.io.*;

public class ChatMessage implements Serializable {

    protected static final long serialVersionUID = 1112122200L;
    // WHOISONLINE to receive the list of connected users
    // MESSAGE normal message
    // LOGOUT self explanatory
    static final int WHOISONLINE = 0, MESSAGE = 1, LOGOUT = 2, REGISTER = 3, BACKLOG = 4,
            BLOCKCHAIN = 5, VALIDATE = 6, VOTES = 7, VALID = 8, INVALID = 9, SHOWCHAIN = 10;
    private final int type;
    private final String message;
    private final Blockchain bchain;

    // constructor
    ChatMessage(int type, String message) {
        this.type = type;
        this.message = message;
        this.bchain = null;
    }

    ChatMessage(int type, Blockchain bchain) {
        this.type = type;
        this.bchain = bchain;
        this.message = "";
    }

    // getters
    int getType() {
        return type;
    }

    String getMessage() {
        return message;
    }

    Blockchain getBlock() {
        return bchain;
    }
}
