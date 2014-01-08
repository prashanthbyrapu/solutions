package controllers;

import play.*;
import play.mvc.*;

import java.util.*;



import play.mvc.results.RenderJson;

public class Application extends Controller {

    public static void index() {
       throw new RenderJson("FInally Prashanth..you wasted almost a day");
    }

}