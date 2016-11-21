package common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Loggable {

    /**
     * Log severity level of 'before' and 'after' log statements.
     */
    enum Level {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    /**
     * Defines the severity which should be used when logging the method arguments.
     */
    Level level() default Level.INFO;
}