package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.dao.ArtistDAO;
import project.entity.Artist;

@Component
public class ArtistValidator implements Validator {

    private final ArtistDAO artistDAO;

    @Autowired
    public ArtistValidator(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Artist.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Artist artist = (Artist) target;

        if(artistDAO.showArtistByNickname(artist.getArtistNickname().isPresent())) {
            errors.rejectValue("fullName", "", "Artist with this nickname has already exist");
        }
    }
}
