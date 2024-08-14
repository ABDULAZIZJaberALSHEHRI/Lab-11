package com.example.lab11.Repository;

import com.example.lab11.Model.Category;
import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.category_Id=?1")
    Category searchCategoryByID(int category_Id);

    @Query("select c from Category c where c.name=?1")
    Category searchCategoryByName(String name);
}
