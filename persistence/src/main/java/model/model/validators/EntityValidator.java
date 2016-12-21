package model.model.validators;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A validator to check if the provided field names, constraints, etc. satisfy actual entity (table) structure.
 * @author yegor
 */
public interface EntityValidator {
    boolean hasField(String fieldName);

    boolean hasFields(List<String> fields);

    default boolean isConditionCorrect(String condition){
        Pattern conditionRegExp = Pattern.compile("(\\w+)\\s*[=<>]\\s*(\\w+)");
        Matcher conditionMatcher = conditionRegExp.matcher(condition);

        if (conditionMatcher.matches())
            return hasField(conditionMatcher.group(1));
        else
            throw new IllegalArgumentException(String.format("incorrect condition expression: %s%n", condition));
    }
}
