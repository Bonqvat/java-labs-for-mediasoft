package laba4;

import java.lang.annotation.*;

// --- юммнрюжхъ дкъ JSON-хлемх онкъ ---
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
    String name();
}