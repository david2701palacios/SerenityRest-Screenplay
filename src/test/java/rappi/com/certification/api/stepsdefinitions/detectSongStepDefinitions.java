package rappi.com.certification.api.stepsdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static jxl.biff.FormatRecord.logger;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class detectSongStepDefinitions {
    @Before
    public void configuracionBaseUrl(){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("David");
        String theRestApiBaseUrl = "https://shazam.p.rapidapi.com/songs";
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("la aplicación consulta el endpoint de detectar una canción")
    public void laAplicaciónConsultaElEndpointDeDetectarUnaCanción() {
        System.out.println("Que vaaaaa");
        OnStage.theActorInTheSpotlight().attemptsTo(
                Post.to("/detect")
                        .with(request -> {
                            try {
                                return request
                                        .header("x-rapidapi-key", "48f87f62c8mshceeba901aab969dp1c6187jsnd4d34dcd3322")
                                        .header("x-rapidapi-host","shazam.p.rapidapi.com")
                                        .body(Files.readString(Paths.get("src/test/resources/data/detect.txt")));
                            } catch (IOException e) {
                                logger.error("Error reading the request body file", e);
                                throw new RuntimeException("Error processing the request", e);
                            }
                        })
        );
    }
    @Then("el sistema responde con la información de la canción encontrada según los metadatos")
    public void elSistemaRespondeConLaInformaciónDeLaCanciónEncontradaSegúnLosMetadatos() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta de la API es 200", response -> response.statusCode(200))
        );
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta contiene el nombre de la canción",
                        response -> response.body("track.title",
                                equalTo("Clint Eastwood")))
        );
    }
}
