package laba4;

import java.lang.annotation.*;

// --- юммнрюжхъ дкъ сярюпебьху щкелемрнб ---
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DeprecatedEx {
    String message();
}