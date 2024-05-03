package com.example.springboot.contollers;



//import com.example.demo.models.Artist;
//import com.example.demo.models.Country;
//import com.example.demo.models.Museum;
//import com.example.demo.models.User;
//import com.example.demo.repositories.MuseumRepository;
//import com.example.demo.repositories.UserRepository;
import com.example.springboot.models.Museum;
import com.example.springboot.models.User;
import com.example.springboot.repositories.MuseumRepository;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MuseumRepository museumRepository;

    @GetMapping("/users")
    public List getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) throws Exception {
        try {
            User nc = userRepository.save(user);
            return new ResponseEntity<Object>(nc, HttpStatus.OK);

        } catch (Exception ex) {
            String error;
            if (ex.getMessage().contains("users.login_UNIQUE"))
                error = "useralreadyexists";
            else if (ex.getMessage().contains("users.email_UNIQUE"))
                error = "emailalreadyexists";
            else
                error = "undefinederror";
            Map<String, String> map = new HashMap<>();
            map.put("error", error);

            return ResponseEntity.ok(map);

        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @RequestBody User userDetails) {
        User user = null;
        Optional<User> uu = userRepository.findById(userId);
        if (uu.isPresent()) {
            user = uu.get();
            user.login = userDetails.login;
            user.email = userDetails.email;
            /*for (Museum m : userDetails.museums) {
                Optional<Museum> mmm = museumRepository.findById(m.id);
                if (mmm.isPresent()) {
                    user.addMuseum(m);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "museum not found");
                }
            }*/
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }

    }


    @PostMapping("/users/{id}/addmuseums")
    public ResponseEntity<Object> addMuseums(@PathVariable(value = "id") Long userID,
                                             @Validated @RequestBody Set<Museum> museums) {
        Optional<User> uu = userRepository.findById(userID);
        int cnt = 0;

        if (uu.isPresent()) {
            User u = uu.get();
            for(Museum m: museums) {
                Optional<Museum> mm = museumRepository.findById(m.id);
                if (mm.isPresent()) {
                    u.addMuseum(mm.get());
                    ++cnt;
                }
            }
            userRepository.save(u);
        }
        Map<String, String> response = new HashMap<>();
        response.put("added", String.valueOf(cnt));
        return ResponseEntity.ok(response);
    }




    @PostMapping("/users/{id}/removemuseums")
    public ResponseEntity<Object> removeMuseums(@PathVariable(value = "id") Long userId,
                                                @Validated @RequestBody Set<Museum> museums) {
        Optional<User> uu = userRepository.findById(userId);
        int cnt = 0;

        if (uu.isPresent()) {
            User u = uu.get();
            for (Museum m: museums) {
                u.removeMuseum(m);
                ++cnt;
            }

            userRepository.save(u);
        }

        // Формируем ответ
        Map<String, String> response = new HashMap<>();
        response.put("count", String.valueOf(cnt));

        return ResponseEntity.ok(response);
    }
}
















//////////////////////////////////////////////////////
//import com.example.springboot.models.Artist;
//import com.example.springboot.repositories.UserRepository;
//import com.example.springboot.repositories.UserRepository;
////import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.example.springboot.models.User;
//import org.springframework.web.server.ResponseStatusException;
//
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/api/v1")
//public class UserController {
//    @Autowired
//    UserRepository countryRepository;
//
//    @GetMapping("/users")
//    public List
//    getAllCountries() {
//        return countryRepository.findAll();
//    }
//    //    @PostMapping("/countries")
////    public ResponseEntity<Object> createUser(@RequestBody User country) {
////        User nc = countryRepository.save(country);
////        return new ResponseEntity<Object>(nc, HttpStatus.OK);
////    }
//    @PostMapping("/users")
//    public ResponseEntity<Object> createUser(@RequestBody User country)
//            throws Exception {
//        try {
//            User nc = countryRepository.save(country);
//            return new ResponseEntity<Object>(nc, HttpStatus.OK);
//        }
//        catch(Exception ex) {
//            String error;
//            if (ex.getMessage().contains("countries.name_UNIQUE"))
//                error = "countyalreadyexists";
//            else
//                error = "undefinederror";
//            Map<String, String>
//                    map =  new HashMap<>();
//            map.put("error", error);
//            map.put("admin_message","u fool");
//            return new ResponseEntity<Object> (map, HttpStatus.OK);
//        }
//    }
//
////    @PutMapping("/users/{id}")
////    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long countryId,
////                                                 @RequestBody User countryDetails) {
////        User user = null;
////        Optional<com.example.springboot.models.User>
////                cc = countryRepository.findById(countryId);
////        if (cc.isPresent()) {
////            user = cc.get();
////            user.login = countryDetails.login;
////            countryRepository.save(user);
////            return ResponseEntity.ok(user);
////        } else {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "country not found");
////        }
////    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
//                                           @RequestBody User userDetails) {
//
//        User user = null;
//        Optional
//                uu = countryRepository.findById(userId);
//        if (uu.isPresent()) {
//            user = (User) uu.get();
//            user.login = userDetails.login;
//            user.email = userDetails.email;
//            countryRepository.save(user);
//            return ResponseEntity.ok(user);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
//        }
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long countryId) {
//        Optional<com.example.springboot.models.User>
//                country = countryRepository.findById(countryId);
//        Map<String, Boolean>
//                resp = new HashMap<>();
//        if (country.isPresent()) {
//            countryRepository.delete(country.get());
//            resp.put("deleted", Boolean.TRUE);
//        }
//        else
//            resp.put("deleted", Boolean.FALSE);
//        return ResponseEntity.ok(resp);
//    }
//
//
////    @GetMapping("/countries/{id}/artists")
////    public ResponseEntity<List<Artist>> getUserArtists(@PathVariable(value = "id") Long countryId) {
////        Optional<User> cc = countryRepository.findById(countryId);
////        if (cc.isPresent()) {
////            return ResponseEntity.ok(cc.get().artists);
////        }
////        return ResponseEntity.ok(new ArrayList<Artist>());
////    }
//
//
//
//}