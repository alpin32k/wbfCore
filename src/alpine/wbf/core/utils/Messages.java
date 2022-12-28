package alpine.wbf.core.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Messages {

    CONSOLE_SENDER_ERROR("&cError: Nie możesz użyć tej komendy z konsoli!", true),
    PERMISSION_DENIED("&cNie posiadasz odpowiednich uprawnień!", true),
    PERMISSION_DENIED_WITH_PERM("&cNie posiadasz odpowiednich uprawnień! (&3{1})", true),
    INVALID_ARGS("&cNiepoprawne użycie! Użyj: &7{1}", true),
    INVALID_ARGS_NOPREFIX("&cNiepoprawne użycie! Użyj: &7{1}"),

    GAMEMODE_INVALID_MODE("&cNiepoprawny tryb! Użyj: &6/gamemode &3[creative, survival, spectator, adventure]", true),
    GAMEMODE_INVALID_PLAYER("&cGracz: &6{1}&c nie jest online.",true),
    GAMEMODE_SUCCESS_TARGET("&7Twój tryb został ustawiony na:&6 {1}&7", true),
    GAMEMODE_SUCCESS_SENDER("&7Graczowi: &6{1} &7ustawiłeś tryb na &6{2}", true),

//    FLY_INVALID_PLAYER("&cThe player '{1}' is not online!", true),
//    FLY_SUCCESS_TARGET("Your flight has been {1}.", true),
//    FLY_SUCCESS_SENDER("You have {1} {2}'s flight.", true),

//    SPEED_INVALID_SPEED("&cInvalid speed!", true),
//    SPEED_SUCCESS("Your {1} speed has been set to &a{2}", true),

    SETSPAWN_SUCCESS("&7Spawn został &apomyślnie&7 ustawiony!", true),
    SPAWN_MISSING("&cSpawn nie został ustawiony!", true),
    SPAWN_TELEPORT("&7Zostałes przeteleportowany na spawn!", true),

    //TELEPORT WRAPPER MESSAGES

    TELEPORT_WRAPPER_TELEPORTED("&7Zostałes przeteleportowany na &6{1}&7!", true),
    TELEPORT_WRAPPER_MOVED("&cRuszyłeś się! Teleport na &6{1}&c został anulowany!", true),
    TELEPORT_WRAPPER_QUEUE("&7Zostaniesz przeniesiony za &6{1}&7 ({2})", true),
    TELEPORT_WRAPPER_BUSY("&cAnulowano! Teleportacja aktualnie trwa", true),

//    WARP_LIST("Warps: {1}", true),
//    WARP_SUCCESS("You have warped to {1}.", true),
//    WARP_NO_PERMISSION("You do not have permission to warp here!", true),
//    WARP_NONE_FOUND("You do not have access to any warps!", true),
//    WARP_INVALID("The warp '{1}' does not exist!", true),

//    SETWARP_SUCCESS("You have set the warp {1}.", true),
//    DELWARP_SUCCESS("You have deleted the warp {1}.", true),
//    DELWARP_ERROR("The warp {1} does not exist!", true),

//    MESSAGE_INVALID_PLAYER("&cThe player '{1}' is not online!", false),
//    MESSAGE_REPLY_INVALID("&cYou don't have a player to respond to!", false),
//    MESSAGE_SENDER("&8[&eme &8-> &e{1}&8] &f{2}"),
//    MESSAGE_TARGET("&8[&e{1} &8-> &eme&8] &f{2}"),

//    KIT_LIST("Kits: {1}", true),
//    KIT_LIST_EMPTY("You do not have any kits.", true),
//    KIT_NO_PERMISSION("You do not have permission to use this kit.", true),
//    KIT_RECEIVED("You have received the &a{1}&d kit.", true),
//    KIT_COOLDOWN("You cannot use this kit for &c{1}&d.", true),
//    KIT_NOT_EXIST("The kit '&e{1}&d' does not exist..", true),
//    KIT_SENT("You have given &e{1}&d the &e{2}&d kit.", true),
//    KIT_INVALID_PLAYER("The player '&e{1}&d' is not online.", true),

//    HAT_NOTHING_IN_HAND("&dYou must have something in your hand.", true),
//    HAT_SUCCESS("&dYour hat has been set.", true),

//    TPA_INVALID_PLAYER("&cThe player '&e{1}&c' is not online."),
//    TPA_SELF("&cYou cannot teleport to yourself."),
//    TPA_SENT("&dRequested to teleport to &e{1}&d."),
//    TPA_RECEIVED("&e{1}&d has requested to teleport to you.<newline>&dType &e/tpaccept&d to accept."),
//    TPA_SPAM("&e{1}&c already has a pending /tpa request from you."),
//    TPA_TOGGLED("&e{1}&c has teleport requests disabled."),


//    TPAHERE_SENT("&dRequested &e{1}&d to teleport to you."),
//    TPAHERE_RECEIVED("&e{1}&d has requested that you teleport to them.<newline>&dType &e/tpaccept&d to accept."),

//    TPACCEPT_TARGET("&dTeleporting to &e{1}&d..."),
//    TPACCEPT_SENDER("&e{1}&d is teleporting to you..."),
//    TPACCEPT_NOREQUEST("&cYou do not have any pending /tpa requests."),
//    TPACCEPT_OFFLINE("&cThe player '&e{1}&c' is no longer online. Cancelling request..."),

//    TPDENY_SENDER("&cDenied &e{1}&c's teleport request."),
//    TPDENY_TARGET("&cYour teleport request was denied by &e{1}&c."),
//    TPDENY_NOREQUEST("&cYou do not have any pending /tpa requests."),
//
//    TPTOGGLE_SUCCESS("&dTeleport requests have been {1}&d."),

    BACK_NOTFOUND("&cYou do not have a location to go back to."),
    BACK_SUCCESS("&dTeleported back to your previous location."),

//    TP_INVALID_PLAYER("&cThe player '&e{1}&c' is not online!"),
//    TP_SUCCESS("&dTeleporting to &e{1}&d..."),
//    TP_OTHERS("&dTeleporting &e{1} &dto &e{2}&d..."),

    EAT_SUCCESS("&7Zostałeś najedzony!", true),
    EAT_SUCCESS_OTHER("&7Gracz: &6{1}&7 został nakarmiony!", true),
    EAT_INVALID_PLAYER("&cGracz: &6{1}&c nie jest online.", true),

    ECHEST_INVALID_PLAYER("&cGracz: &6{1}&c nie jest online.", true),
    ECHEST_OPEN_OTHER("&7Otworzyłeś &3enderchesta &7gracza: &6{1}&7!", true),

    CLEAR_INVALID_PLAYER("&cThe player &e{1}&c is not online.", true),
    CLEAR_SUCCESS("&aYour inventory has been cleared.", true),
    CLEAR_OTHER_SUCCESS("&e{1}&a's inventory has been cleared.", true),

    TIME_SET("&aPomyślnie &7ustawiono czas na serwerze na: &6{1}&7.", true),

    WEATHER_SET("&aPomyślnie &7ustawiono pogodę na: &6{1}&7.", true),

    CRAFT_SUCCESS("&7Otwieranie przenośnego craftingu", true),
//
//    SOCIALSPY_TOGGLED("&eSocial Spy has been {1}&e."),
//    SOCIALSPY_VIEW("&eSS: &8(&e{1} &7-> &e{2}&8) &7{3}"),

    SETHOME_LIMIT("&cOsiągnąłeś limit: &7{1}&c swoich domów!"),
    SETHOME_SUCCESS("&aPomyślnie&7 zapisałeś dom: &6{1}&7.", true),
    SETHOME_INVALID_NAME("&cNie możesz użyc nazwy &6{1}&c!", true),
    SETHOME_BUSY_NAME("&cNie możesz użyc nazwy &6{1}&c (nazwa zajęta)!", true),

    HOME_INVALID("&cNie masz takiego domu!", true),
    HOME_SUCCESS("&7Teleportujesz się do domu: &6{1}&7...", true),
    HOME_LIST("&7Twoje domy: &6{1}", true),
    HOME_NONE("&cNie masz ustawionego żadnego domu!", true),

    DELHOME_INVALID("&cNie masz takiego domu!", true),
    DELHOME_SUCCESS("&aPomyślnie &7usunięto twój dom: &6{1}&7.", true),

    DISCORD_SERVER("&7Dołącz na naszego discorda: &6{1}", true),

    INVSEE_INVALID_PLAYER("&cGracz: '&6{1}&c' nie jest online!", true),
    INVSEE_OPEN("&7Otworzyłeś ekwipunek gracza: &6{1}&7", true),

    FIX_INVALID("&cThat item can not be fixed."),
    FIX_SUCCESS("&aThe item in your hand has been fixed."),
    FIX_COOLDOWN("&cYou cannot fix another item for &e{1}&c."),

    NICK_LIMIT("&cThis nickname is too long."),
    NICK_RESET_PLAYER("&dYour nickname has been reset."),
    NICK_RESET_OTHER("&e{1}&d's nickname has been reset."),
    NICK_SET_PLAYER("&dYour nickname has been set to &e{1}"),
    NICK_SET_OTHER("&e{1}&d's nickname has been set to &e{2}"),
    NICK_INVALID_PLAYER("&cCould not find the player &e{1}&c."),

    SEEN_INVALID_PLAYER("&cThe player &e{1}&c does not exist."),
    SEEN_SUCCESS("&dThe player &e{1}&d was last seen &e{2}&d ago."),
    SEEN_ONLINE("&e{1}&d is online right now."),

    GOD_SUCCESS("&dGod mode has been {1}&d."),

    ;

    private String PATH = "messages.lang";

    private String message;
    private boolean prefixed;

    Messages(String message) {
        this(message, false);
    }

    Messages(String message, boolean prefixed) {
        this.message = message;
        this.prefixed = prefixed;
    }

    public void send(CommandSender player, String... args) {
        if (prefixed) {
            MessageUtils.sendPrefixedMessage(player, doArgs(player, args));
        } else {
            MessageUtils.sendMessage(player, doArgs(player, args));
        }
    }

    private String doArgs(CommandSender player, String... args) {
        String currentMessage = message;
        for (int i = 0; i < args.length; i++) {
            currentMessage = currentMessage.replace("{" + (i + 1) + "}", args[i]);
        }
        return currentMessage;
    }

}