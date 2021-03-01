import DAO.EndangeredDAO;
import DAO.SightingsDAO;
import DAO.WildlifeDAO;
import DAO.sql2oAnimalDAO;
import models.Endangered;
import models.Sightings;
import models.Wildlife;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
//import static spark.debug.DebugScreen.enableDebugScreen;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
//        enableDebugScreen();
        System.out.println("TESTING PASSED");

//        postgresql://xdlwnxlgaeznei:1b070c99d049a766aebb058cf6fae3f53e725b1d7ad2897bb7c3e069d8af1922@ec2-50-16-108-41.compute-1.amazonaws.com:5432/d5g37r2soo5f6s
        staticFileLocation("/public");
//       String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker2";
        String connectionString = "jdbc:postgresql://xdlwnxlgaeznei:1b070c99d049a766aebb058cf6fae3f53e725b1d7ad2897bb7c3e069d8af1922@ec2-50-16-108-41.compute-1.amazonaws.com:5432/d5g37r2soo5f6s";

//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

        Sql2o sql2o = new Sql2o(connectionString, "xdlwnxlgaeznei", "1b070c99d049a766aebb058cf6fae3f53e725b1d7ad2897bb7c3e069d8af1922");
        sql2oAnimalDAO sql2oAnimalDAO = new sql2oAnimalDAO (sql2o);
        SightingsDAO sightingsDAO = new SightingsDAO(sql2o);
        EndangeredDAO endangeredDAO = new EndangeredDAO(sql2o);
        WildlifeDAO wildlifeDAO = new WildlifeDAO(sql2o);


        Map<String, Object> model = new HashMap<>();

        String young = null;
        Wildlife animal = new Endangered ("hippo", "endangered","ill","young", young);
        System.out.println(animal.getAnimalName());



        get("/", (req, res) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/add-wildlife", (request, response) -> {
            return new ModelAndView(model, "wildlife.hbs");
        }, new HandlebarsTemplateEngine());


        post("/add-wildlife", (request, response) -> {
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");
            String name = request.queryParams("name");
            String ranger = request.queryParams("ranger");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            if (age == null && health == null) {
                sql2oAnimalDAO.addAnimalName(name);
            } else {
                endangeredDAO.addAnimalName(name);
                endangeredDAO.saveHealthOfAnimal(health);
                endangeredDAO.saveAgeOfAnimal(age);
            }
            Sightings sightings = new Sightings(location, ranger, animalId);
            sightingsDAO.addSightings(sightings);
            response.redirect("/all-animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/all-animals", (request, response) -> {
            model.put("animals", endangeredDAO.getAllEndangeredAnimals());
            model.put("common",sql2oAnimalDAO.getAllAnimals());

            return new ModelAndView(model, "allAnimals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            model.put("sight", sightingsDAO.getAllSightings());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());
    }

}