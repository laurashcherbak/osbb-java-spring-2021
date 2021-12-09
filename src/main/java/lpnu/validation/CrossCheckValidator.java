package lpnu.validation;


import lpnu.validation.enumeration.Condition;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CrossCheckValidator implements ConstraintValidator<CrossCheck, Object> {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    private String field1;
    //private String field2;
    private Condition condition;


    @Override
    public void initialize(final CrossCheck constraintAnnotation) {
        field1 = constraintAnnotation.field1();
        //field2 = constraintAnnotation.field2();
        condition = constraintAnnotation.condition();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return (Boolean) PARSER.parseExpression(field1 + " " + condition.getCondition() + " " + 0).getValue(value);
    }


}