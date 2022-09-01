package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.entity.Visitor;
import project.service.VisitorService;

@Component
public class VisitorValidator implements Validator {

    private final VisitorService visitorService;

    @Autowired
    public VisitorValidator(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Visitor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Visitor visitor = (Visitor) target;

        if (visitor.getVisitorSurname()!=null) {
            errors.rejectValue("nickName", "", "Visitor with this nickname has already exist");
        }
    }
}
