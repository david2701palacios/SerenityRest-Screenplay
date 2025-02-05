package rappi.com.certification.api.stepsdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class getSongStepDefinitions {

    @Before
    public void configuracionBaseUrl(){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("David");
        String theRestApiBaseUrl = "https://shazam.p.rapidapi.com/songs/v2";
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("la aplicación consulta el endpoint de obtener una canción")
    public void laAplicaciónConsultaElEndpointDeObtenerUnaCanción(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dataSong = dataTable.asMaps(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                Get.resource("/get-details?id="+dataSong.get(0).get("idCancion")+"&l="+dataSong.get(0).get("idioma"))
                        .with(request -> request.header("x-rapidapi-key", "48f87f62c8mshceeba901aab969dp1c6187jsnd4d34dcd3322")
                                .header("x-rapidapi-host", "shazam.p.rapidapi.com"))
        );

    }
    @Then("el sistema responde con la información de la canción encontrada")
    public void elSistemaRespondeConLaInformaciónDeLaCanciónEncontrada() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta de la API es 200", response -> response.statusCode(200))
        );
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta contiene el nombre de la canción",
                        response -> response.body("data[0].attributes.name",
                        equalTo("Let Me Out (feat. Mavis Staples & Pusha T)")))
        );
    }
    @Then("el sistema responde con un mensaje de error controlado")
    public void elSistemaRespondeConUnMensajeDeErrorControlado() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta de la API es 200", response -> response.statusCode(200))
        );
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta contiene el mensaje de error",
                        response -> response.body("errors[0].title",
                                equalTo("Resource Not Found")))
        );
    }
    @When("la aplicación consulta el endpoint de obtener una canción sin token")
    public void laAplicaciónConsultaElEndpointDeObtenerUnaCanciónSinToken() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Get.resource("/get-details?id=400347158&l=es-ES")
        );
    }
    @Then("el sistema responde con el error de autenticación")
    public void elSistemaRespondeConElErrorDeAutenticación() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta de la API es 401", response -> response.statusCode(401))
        );
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("La respuesta contiene el mensaje de error",
                        response -> response.body("message",
                                equalTo("Invalid API key. Go to https://docs.rapidapi.com/docs/keys for more info.")))
        );
    }
}
