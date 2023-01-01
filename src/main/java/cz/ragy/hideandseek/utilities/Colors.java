package cz.ragy.hideandseek.utilities;

import org.bukkit.ChatColor;

import java.util.List;

public class Colors {
    public static List<String> translate;
    public static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public static String translate(String text) {
        String[] texts = text.split(String.format(WITH_DELIMITER, "&"));
        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < texts.length; i++) {
            if (texts[i].equalsIgnoreCase("&")) {
                i++;
                finalText.append(texts[i].charAt(0) == '#' ? net.md_5.bungee.api.ChatColor.of(texts[i].substring(0, 7)) + texts[i].substring(7) : ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
            } else {
                finalText.append(texts[i]);
            }
        }
        return finalText.toString();
    }
}