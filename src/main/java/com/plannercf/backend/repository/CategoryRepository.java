package com.plannercf.backend.repository;

import com.plannercf.backend.domain.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @NotNull
    Optional<Category> findById(@NotNull Long id);
    @Nonnull
    List<Category> findAll();

    boolean existsById(@NotNull Long id);
}
