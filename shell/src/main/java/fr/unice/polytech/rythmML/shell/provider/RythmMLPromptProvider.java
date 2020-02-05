package fr.unice.polytech.rythmML.shell.provider;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class RythmMLPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("RythmML>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
