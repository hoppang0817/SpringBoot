package com.bit.persistence;


import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Profile;

public interface ProfileReposity extends CrudRepository<Profile, Long> {
	

}
