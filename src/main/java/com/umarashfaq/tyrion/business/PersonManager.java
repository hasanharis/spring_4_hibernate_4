package com.umarashfaq.tyrion.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umarashfaq.tyrion.domain.Person;

@Service
@Transactional
public class PersonManager extends BaseService<Person>{

}
