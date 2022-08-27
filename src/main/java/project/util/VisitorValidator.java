package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.dao.VisitorDAO;
import project.entity.Visitor;

@Component
public class VisitorValidator implements Validator {

    private final VisitorDAO visitorDAO;

    @Autowired
    public VisitorValidator(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Visitor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Visitor visitor = (Visitor) target;

        if (visitorDAO.getVisitorBySurname(visitor.getVisitorSurname()).isPresent()) {
            errors.rejectValue("nickName", "", "Visitor with this nickname has already exist");
        }
    }
}
