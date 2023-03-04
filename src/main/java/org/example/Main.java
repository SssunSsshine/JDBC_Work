package org.example;

import entity.Tourist;
import repository.ConnectionFactory;
import repository.TouristRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        TouristRepository touristRepository = new TouristRepository(new ConnectionFactory());
        System.out.println(touristRepository.get(1L));
        Tourist tourist = new Tourist();
        tourist.setName("Peter");
        tourist.setSurname("Griffin");
        tourist.setBirthday(Date.valueOf("1999-01-10"));
        tourist.setPhone("89129334854");
        tourist.setEmail("grif@gmail.com");
        touristRepository.insert(tourist);

    }
}