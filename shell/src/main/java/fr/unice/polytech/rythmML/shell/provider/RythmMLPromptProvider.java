package fr.unice.polytech.rythmML.shell.provider;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * Prompt configuration.
 */
@Component
public class RythmMLPromptProvider implements PromptProvider {

    /**
     * Prompt configuration.
     *
     * @return the prompt
     */
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("RythmML>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
