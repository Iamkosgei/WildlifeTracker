import org.apache.log4j.BasicConfigurator;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        BasicConfigurator.configure();

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers",Ranger.all());
            model.put("template", "templates/index.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        get("/ranger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/add-new-ranger-form.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        post("/ranger", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String firstName = req.queryParams("inputFirstName");
            String lastName = req.queryParams("inputLastName");
            String badgeNumber =req.queryParams("inputBadgeNumber");

            Ranger ranger = new Ranger(firstName,lastName,Integer.parseInt(badgeNumber));
            ranger.save();

            res.redirect("/");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        get("/ranger/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params(":id")));
            model.put("ranger",ranger);
           // model.put("sightings",ranger.AllMySightings());

            model.put("template", "templates/ranger.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        get("/ranger/:id/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params(":id")));
            model.put("ranger",ranger);



            model.put("animals",Animal.all());
            model.put("locations",Location.all());


            model.put("sightings",Sighting.find(Integer.parseInt(req.params(":id"))));
            model.put("template", "templates/record-sighting.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });


        post("/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            int animal = Integer.parseInt(req.queryParams("animal"));
            String location = req.queryParams("location");

            Sighting sighting = new Sighting(name,location,animal);
            sighting.save();

            res.redirect("/");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });



        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();


            model.put("animals",Animal.all());
            model.put("endangeredAnimals" , EndangeredAnimal.allEndangeredAnimals());

            model.put("template", "templates/animals.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });


        post("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("inputName");
            String health = req.queryParams("healthInput");
            String age = req.queryParams("ageInput");
           if(req.queryParams("endangeredInput") != null)
           {
               EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name,health,age);
               endangeredAnimal.save();
           }
           else
           {
               Animal animal = new Animal(name);
               animal.save();
           }
            res.redirect("/animals");

            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });



        get("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings",Sighting.all());

            model.put("template", "templates/sightings.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });



        get("/locations", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("locations",Location.all());

            model.put("template", "templates/locations.vtl");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });

        post("/locations", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            Location location = new Location(req.queryParams("name"));
            location.save();
            res.redirect("/locations");
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, layout)
            );
        });


    }
}
