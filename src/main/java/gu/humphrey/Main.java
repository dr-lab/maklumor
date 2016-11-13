package gu.humphrey;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(args[0]));

    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");

      get("/hook", (req, res) -> "Hello World Hook 199@@&6");

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());


  }

}
