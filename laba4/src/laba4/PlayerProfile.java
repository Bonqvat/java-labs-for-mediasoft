package laba4;

// --- “≈—“Œ¬€…  À¿—— ƒÀﬂ —≈–»¿À»«¿÷»» ---
public class PlayerProfile {
    @JsonField(name = "nickname")
    private String playerName = "Vitalii";

    @JsonField(name = "level")
    private int currentLevel = 15;

    @JsonField(name = "ffb_strength")
    private int forceFeedback = 80;

    private String internalId = "uuid-hidden";
}