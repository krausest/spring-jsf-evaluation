package net.stefankrause.sj;

import java.lang.annotation.*;
import javax.validation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy=ValidatorGeburtstagImpl.class)
public @interface ValidatorGeburtstag {
    String message();
    
    Class[] groups() default{};
    Class[] payload() default{};
}
