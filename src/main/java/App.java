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

public class App {

//        static int getHerokuAssignedPort() {
//            ProcessBuilder processBuilder = new ProcessBuilder();
//            if (processBuilder.environment().get("PORT") != null) {
//                return Integer.parseInt(processBuilder.environment().get("PORT"));
//            }
//            return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
//        }
    public static void main(String[] args) {
        System.out.println("TESTING PASSED");

//        postgres://nnafcxsyigvkzu:1e72901a534b7d0fdee2c757d29603a57944323ed42a0531a074ad7f9cbee264@ec2-3-232-163-23.compute-1.amazonaws.com:5432/d3ltq59lv9bc7p
//        port(getHerokuAssignedPort());
        staticFileLocation("/public");
       String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
//        String connectionString = "jdbc:postgresql://ec2-3-232-163-23.compute-1.amazonaws.com:5432/d3ltq59lv9bc7p";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

//      Sql2o sql2o = new Sql2o(connectionString, "nnafcxsyigvkzu", "1e72901a534b7d0fdee2c757d29603a57944323ed42a0531a074ad7f9cbee264");
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