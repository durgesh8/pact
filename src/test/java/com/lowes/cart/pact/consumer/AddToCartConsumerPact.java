package com.lowes.cart.pact.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowes.cart.application.model.Cart;
import io.pactfoundation.consumer.dsl.LambdaDslObject;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.smartcardio.CardTerminal;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddToCartConsumerPact extends ConsumerPactTest {
    JSONObject jsonObject = null;
    InputStream inputStream = null;

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("addToCartProvider", "localhost",6060,this);


    @Pact(consumer = "addToCartConsumer",provider="addToCartProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder){

        JSONParser parser = new JSONParser();
        Object obj;
        {
            try {
                inputStream = getClass().getClassLoader().getResourceAsStream("addToCartResponse.json");
                System.out.println("IS "+inputStream);
                if(inputStream!=null){
                    BufferedReader streamReader = new BufferedReader(
                            new InputStreamReader(inputStream, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();

                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);
                    jsonObject = new JSONObject(responseStrBuilder.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Map<String, String> headers= new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("business-channel","DIGITAL_LOWESDESKTOP");
        headers.put("ipAddress","1");
        headers.put("locale","US");

        Consumer<LambdaDslObject> zippedStoreLambda = (store) -> {
            store.stringType("storeId", "6919");
        };
        Consumer<LambdaDslObject> productInfoLambda = (product) -> {
            product.stringType("itemType", "RGL");
            product.stringType("omniItemId", "999930538");
        };

        DslPart body = newJsonBody((o) -> {
            o.array("cartItems", (cartItem) -> cartItem.object(ci -> {
                ci.numberType("quantity", 1);
                ci.object("productInfo", productInfoLambda);
                ci.object("zippedInStore",zippedStoreLambda);
            }));
        }).build();


        return builder.uponReceiving("a request for add to cart")
                .path("http://localhost:6060/cart/#RegularItemATCRequest")
                .method(RequestMethod.POST.name())
                .body(body)
                .willRespondWith()
                .headers(headers)
                .status(201)
                .body(jsonObject)
                .toPact();
    }

    @Override
    protected String providerName() {
        return "addToCartProvider";
    }

    @Override
    protected String consumerName() {
        return "addToCartConsumer";
    }

    @Override
    @PactVerification()
    public void runTest(MockServer mockServer, PactTestExecutionContext pactTestExecutionContext) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity= new HttpEntity<>(null,headers);
        Map expectedResponse = new HashMap();
        RestTemplate template = new RestTemplate();
        String response = template.postForEntity("https://dev.carbon.gcp.lowes.com/guestidentity",entity,String.class).getBody();
        JSONObject json = new JSONObject(response);

        headers.set("x-token",json.get("access_token").toString());
        headers.set("business-channel","DIGITAL_LOWESDESKTOP");
        headers.set("ipAddress","1");
        headers.set("locale","US");
        ObjectMapper objectMapper = new ObjectMapper();
        inputStream = inputStream = getClass().getClassLoader().getResourceAsStream("addToCartRequest.json");
        Cart cart= objectMapper.readValue(inputStream, Cart.class);
        HttpEntity<Cart> cartRequest = new HttpEntity<>(cart,headers);
       HttpEntity<Cart> responseEntity=  template.postForEntity("http://localhost:6060/cart#RegularItemATCRequest",cartRequest,Cart.class);
       assertNotNull(responseEntity.getBody());

    }







}
