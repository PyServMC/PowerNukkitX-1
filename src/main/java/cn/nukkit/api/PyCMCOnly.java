package cn.nukkit.api;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE,
        ElementType.FIELD, ElementType.PACKAGE})
@PyCMCOnly
@Since("PyCMC-v3.1")
@Inherited
@Documented
public @interface PyCMCOnly {
    @PyCMCOnly
    String value() default "";
}
