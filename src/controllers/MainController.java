package controllers;

import models.Restapi;
import views.Maincon;

public class MainController {
    Maincon maincon;
    Restapi restapi;
    public MainController() {
        this.restapi = new Restapi();
        this.maincon = new Maincon(this.restapi);
    }
    
}
