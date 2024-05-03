package com.example.springboot.repositories;

import com.example.springboot.models.Artist;
//import com.example.springboot.models.Country;
import com.example.springboot.models.Museum;
import com.example.springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MuseumRepository  extends JpaRepository<Museum, Long>
{

}