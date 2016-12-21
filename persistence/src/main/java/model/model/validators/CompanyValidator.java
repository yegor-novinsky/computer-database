package model.model.validators;

import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yegor
 */
public class CompanyValidator implements EntityValidator {
    private final List<String> companyTableFields = Arrays.asList("id", "name");

    @Override
    public boolean hasField(String fieldName) {
        return companyTableFields.contains(fieldName);
    }

    @Override
    public boolean hasFields(List<String> fields) {
        return companyTableFields.containsAll(fields);
    }

}
