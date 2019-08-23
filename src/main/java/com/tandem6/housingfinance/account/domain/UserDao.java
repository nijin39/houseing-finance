package com.tandem6.housingfinance.account.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DAOUser findByUsername(String userName);
}
