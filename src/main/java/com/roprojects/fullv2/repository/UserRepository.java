package com.roprojects.fullv2.repository;

import com.roprojects.fullv2.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmail(String email);

    User findByEmailAndStatus(String username, Integer status);

    Page<User> findAll(Pageable pageable);
}
