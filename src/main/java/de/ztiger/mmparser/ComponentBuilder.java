package de.ztiger.mmparser;

import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.tag.TagPattern;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.ztiger.mmparser.MMParser.MM;

@AllArgsConstructor
public class ComponentBuilder {

    private final String key;
    private final List<TagResolver> placeholders = new ArrayList<>();

    /**
     * Adds a single placeholder to the component builder.
     *
     * @param key   The placeholder key
     * @param value The placeholder value
     * @return The updated ComponentBuilder
     */
    public ComponentBuilder with(@TagPattern String key, String value) {
        placeholders.add(Placeholder.parsed(key, value));
        return this;
    }

    /**
     * Adds a single component placeholder to the component builder.
     *
     * @param key The placeholder key
     * @param value The component value
     * @return The updated ComponentBuilder
     */
    public ComponentBuilder with(@TagPattern String key, Component value) {
        placeholders.add(Placeholder.component(key, value));
        return this;
    }

    /**
     * Adds placeholders to the component builder.
     *
     * @param placeholders The placeholders to add
     * @return The updated ComponentBuilder
     */
    public ComponentBuilder with(TagResolver... placeholders) {
        Collections.addAll(this.placeholders, placeholders);
        return this;
    }

    /**
     * Builds the component using the provided MMParser with standard behaviour (Prefix enabled).
     *
     * @return the built Component
     */
    public Component build() {
        return build(false);
    }

    /**
     * Builds the component using the provided MMParser.
     *
     * @param usePrefix whether to use the prefix
     * @return the built Component
     */
    public Component build(boolean usePrefix) {
        String message;

        if (MMParser.getMESSAGE_CACHE().containsKey(key)) {
            message = MMParser.getMESSAGES().getString(key);
        } else {
            message = MMParser.getMESSAGES().getString(key);
            MMParser.getMESSAGE_CACHE().put(key, message);
        }

        if (usePrefix) {
            String prefix = MMParser.getMESSAGES().getString("prefix");
            if (prefix != null) message = prefix + message;
        }

        if (message == null) message = key;
        return MM.deserialize(message, placeholders.toArray(TagResolver[]::new)).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }
}
