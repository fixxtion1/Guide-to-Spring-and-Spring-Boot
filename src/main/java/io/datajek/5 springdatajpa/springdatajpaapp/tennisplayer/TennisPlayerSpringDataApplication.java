package io.datajek.springdatajpaapp.tennisplayer;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisPlayerSpringDataApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlayerSpringDataRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(TennisPlayerSpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Inserting new Players
        logger.info("\n\n>>Inserting Player: {}\n", repo.save(new Player("Djokovic", "Serbia", Date.valueOf("1987-05-22"), 81)));
        logger.info("\n\n>>Inserting Player: {}\n", repo.save(new Player("Monfils", "France", Date.valueOf("1986-09-01"), 10)));
        logger.info("\n\n>>Inserting Player: {}\n", repo.save(new Player("Thiem", "Austria", new Date(System.currentTimeMillis()), 17)));
        //Updating an existing Player
        logger.info("\n\n>>Updating Player with Id 3: {}\n", repo.save(new io.datajek.springdatajpaapp.tennisplayer.Player(3, "Thiem", "Austria", Date.valueOf("1993-09-03"), 17)));
        //Finding a Player with given id
        logger.info("\n\n>>Player with Id 2: {}\n", repo.findById(2));
        //Deleting a Player with given id
        repo.deleteById(2);
        //Finding all Players in the table
        logger.info("\n\n>>All Players Data: {}\n", repo.findAll());
    }

}