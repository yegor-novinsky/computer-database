package model.model.validators;

import java.util.Arrays;
import java.util.List;

/**
 * @author yegor
 */
public class ComputerValidator implements EntityValidator {
    private final List<String> computerTableFields = Arrays.asList("id", "name", "introduced", "discontinued", "company");

    @Override
    public boolean hasField(String fieldName) {
        return computerTableFields.contains(fieldName);
    }

    @Override
    public boolean hasFields(List<String> fields) {
        return computerTableFields.containsAll(fields);
    }
}
