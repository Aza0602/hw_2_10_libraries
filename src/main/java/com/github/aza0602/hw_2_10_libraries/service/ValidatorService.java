package com.github.aza0602.hw_2_10_libraries.service;

import com.github.aza0602.hw_2_10_libraries.exception.IncorrectLastNameException;
import com.github.aza0602.hw_2_10_libraries.exception.IncorrectNameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidatorService {

    public String validateName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname) {
        if (!StringUtils.isAlpha(surname)) {
            throw new IncorrectLastNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }
}
