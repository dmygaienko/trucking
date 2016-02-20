package com.mygaienko.trucking.service;

import com.mygaienko.trucking.model.Contact;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by enda1n on 20.02.2016.
 */
@Service
public class ContactService {
    public List<Contact> findAll() {
        return Collections.emptyList();
    }

    public void save(Contact contact) {
    }
}
