
import config.ApplicationConfig;
import exception.ExceptionHandler;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import routes.Routes;

public class Main {

    private static final Routes routes = new Routes();
    private static final Javalin app = Javalin.create();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandler();
    private static int count = 0;

    private static void requestInfoHandler(Context ctx) {
        String requestInfo = ctx.req().getMethod() + " " + ctx.req().getRequestURI();
        ctx.attribute("requestInfo", requestInfo);
    }

    public static void main(String[] args) {

        app.before(Main::requestInfoHandler);
        app.updateConfig(ApplicationConfig::configuration);

        app.post("/hej/{id}", ctx -> ctx.result("Hello World"));

        app.routes(routes.getRoutes(app));

        app.after(ctx -> LOGGER.info(" Request {} - {} was handled with status code {}", count++, ctx.attribute("requestInfo"), ctx.status()));

        app.start(7300);
    }
}
