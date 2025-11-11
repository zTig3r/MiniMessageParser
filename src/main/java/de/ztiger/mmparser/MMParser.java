package de.ztiger.mmparser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;

@AllArgsConstructor
public class MMParser {

    /**
     * The MiniMessage instance for parsing messages.
     */
    @Getter
    protected static final MiniMessage MM = MiniMessage.miniMessage();

    /**
     * The YAML configuration containing the messages.
     */
    @Getter
    @Setter
    public static YamlConfiguration MESSAGES;

    /**
     * A cache for messages to improve performance.
     */
    @Getter
    protected static HashMap<String, String> MESSAGE_CACHE = new HashMap<>();

    /**
     * Reloads the messages configuration with new messages.
     *
     * @param newMessages The new YAML configuration containing messages.
     */
    public void reloadMessages(YamlConfiguration newMessages) {
        setMESSAGES(newMessages);
        getMESSAGE_CACHE().clear();
    }
}
