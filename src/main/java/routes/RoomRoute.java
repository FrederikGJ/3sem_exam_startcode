package routes;

import io.javalin.apibuilder.EndpointGroup;
import controller.RoomController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RoomRoute {

    private final RoomController roomController = new RoomController();

    protected EndpointGroup getRoutes() {

        return () -> {
            path("/rooms", () -> {
                post("/hotel/{id}", roomController::create);
                get("/", roomController::readAll);
                get("/{id}", roomController::read);
                put("/{id}", roomController::update);
                delete("/{id}", roomController::delete);
            });
        };
    }
}
