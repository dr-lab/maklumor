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

      get("/hook", (req, res) -> {
          System.out.println("headers=" + req.headers());
          System.out.println("attributes=" + req.attributes());
          System.out.println("body=" + req.body());
          System.out.println("cookies="+req.cookies());
          System.out.println("host="+req.host());
          System.out.println("params=" + req.params());
          System.out.println("queryString="+req.queryString());
          System.out.println("queryMap="+req.queryMap());
          System.out.println("queryMap="+req.queryMap().toMap());
          System.out.println("queryParams="+req.queryParams());
          System.out.println("hub.mode="+req.queryParams("hub.mode")+"; hub.verify_token="+req.queryParams("hub.verify_token") + "; hub.challenge="+req.headers("hub.challenge"));
          if ("subscribe".equals(req.queryParams("hub.mode")) &&
                  "1234567890".equals(req.queryParams("hub.verify_token"))) {
              System.out.println("Validating webhook");
              //res.status(200).send(req.query['hub.challenge']);
              return req.headers("hub.challenge");
          } else {
              System.out.println("Failed validation. Make sure the validation tokens match.");
              return "token code not match";
          }
      });

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());


  }

}
