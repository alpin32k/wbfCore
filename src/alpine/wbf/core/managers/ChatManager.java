package alpine.wbf.core.managers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.EventHandler;

import java.util.HashMap;
import java.util.UUID;

public class ChatManager {

    public @Getter @Setter
    boolean chatEnable;

    public HashMap<UUID, Integer> commandSlowDown = new HashMap<UUID, Integer>();

    public ChatManager()
    {
        this.chatEnable = true;
    }

}
