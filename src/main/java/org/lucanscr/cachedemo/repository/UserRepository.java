package org.lucanscr.cachedemo.repository;

import org.lucanscr.cachedemo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
