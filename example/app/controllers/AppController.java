package controllers;

import play.mvc.*;
import play.libs.Json;
import services.DataService;
import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.*;

import javax.inject.*;

import com.google.gson.Gson;
import nz.rafikn.datatables.request.Request;
import nz.rafikn.datatables.response.DataTable;

@Singleton
public class AppController extends Controller {

    private final DataService dataService;

    @Inject
    public AppController(DataService dataService) {
      this.dataService = dataService;
    }

    public Result index() {
        return ok(index.render());
    }

    public CompletionStage<Result> data() {
      Map<String, String[]> map = request().body().asFormUrlEncoded();

      return CompletableFuture.supplyAsync(() -> dataService.getDataTable(Request.build(map)))
                 .thenApply(dataTable -> ok(gson.toJson(dataTable)));
    }

    private static final Gson gson = new Gson();

}
