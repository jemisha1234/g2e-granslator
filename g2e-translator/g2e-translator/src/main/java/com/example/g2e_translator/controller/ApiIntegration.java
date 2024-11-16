package com.example.g2e_translator.controller;

import com.example.g2e_translator.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


// This controller handles the translation functionality (Gujarati to English).
/*@Controller
public class TranslateController {

    // Inject the TranslationService to handle the logic for translating words.
    @Autowired
    private TranslationService translationService;

    // This method handles GET requests to "/translate" and displays the translation
    // page.
    @GetMapping("/translate")
    public String showTranslatePage() {
        return "translate"; // Return the Thymeleaf template that displays the translation form.
    }

    // This method handles POST requests to "/translate" when the user submits a
    // word to translate.
    @PostMapping("/translate")
    public String translateWord(
            @RequestParam("gujarati") String gujaratiWord, // The Gujarati word submitted by the user.
            Model model) { // Model is used to pass the translated result to the view.

        // Call the service to translate the Gujarati word to English.
        String translation = translationService.translateGujaratiToEnglish(gujaratiWord);

        // Add the translation and the original Gujarati word to the model to display
        // them in the view.
        model.addAttribute("translation", translation);
        model.addAttribute("gujaratiWord", gujaratiWord);

        return "translate"; // Return the same translation page with the result.
    }
}*/

/*const res = await fetch("https://libretranslate.com/translate", {
	method: "POST",
	body: JSON.stringify({
		q: "",
		source: "auto",
		target: "en",
		format: "text",
		alternatives: 3,
		api_key: ""
	}),
	headers: { "Content-Type": "application/json" }
});

console.log(await res.json());
 * 
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;


@Controller
public class ApiIntegration{

    public ApiIntegration()
    {
        String url = "https://libretranslate.com/translate";
        String ApiKey = "";
        try{

            //reate HttpClient instance object
            HttpCleint client = HttpClient.newHttpClient();

            //Createa HttpRequst now
            HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("Authorization","Bearer "+ApiKey )       
                                .header("Content-Type","application/json")
                                .GET()
                                .build();

            //Send your request to the website offering API services
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            // Check the validity of the response from the website offering API services

            if(response.statusCode()==200)
            {
                System.out.println(response.body());
            }
            else{
                System.out.println("Problem encountered: " + response.statusCode());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    }





