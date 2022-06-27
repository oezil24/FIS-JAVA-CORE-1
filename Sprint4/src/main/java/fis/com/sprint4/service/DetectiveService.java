package fis.com.sprint4.service;
import fis.com.sprint4.entity.Detective;

import java.util.Set;

public interface DetectiveService {
    Detective addDetective(Detective detective);

    Detective updateDetective(Detective detective);

    Set<Detective> getDetectives();

    Detective getDetective(Long id);

    void deleteDetective(Long id);

}
