package com.example.springboot.repositories;

import com.example.springboot.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CountryRepository  extends JpaRepository<Country, Long>
{

}