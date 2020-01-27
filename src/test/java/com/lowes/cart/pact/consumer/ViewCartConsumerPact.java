package com.lowes.cart.pact.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.lowes.cart.application.model.Cart;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewCartConsumerPact extends ConsumerPactTest {
    @Override
    @Pact(provider="ExampleProvider", consumer="BaseConsumer")
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> headers2 = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers2.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("business-channel","DIGITAL_LOWESDESKTOP");
        headers.put("ipAddress","1.1.1.1");
        headers.put("locale","US");
        headers.put("x-token","eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE1Nzk4NDU2NjMsImV4cCI6MTU3OTkzMjA2Mywic3ViIjoiNGQwODE0YWMtNzRmNy00N2RmLTljYjAtMTA0NGZhZDY2MDZjIiwic291cmNlIjoibG93ZXMiLCJzdGF0dXMiOiJHIiwidG9rZW5UeXBlIjoiYWNjZXNzX3Rva2VuIn0.Dt3wPoWaXkqc32neIPSFwBSNxX2yeifjVDC8lRMmtuxKe86wTMU_MK2GI76H0QAlaLe6fWzVLykdrpWXTNeUylBdkd08Yig5UVyRFj1jdiwhr4MXQR1ZATVp_Vw6SiSgNEr8KFh1PYj_tKnL3iChI7c2ubGkqmmSmt49ZlYtUPRaMN2NTEDbJWNCNq2RFxdiq2ez6T79csjAaIQxMpf2kVddkTFCtR1dQsvQKm_UQ2mRuwR_NZ6O9tRzL-ECmYXJnOzu8iW4rNnmIrCo9syZYi755WqaH8irpF4cy83YwYA6kyrzRrPF0ZSya16CsT6bVnK9DC0FcVCZAUO8oAZ9qg");

        JSONParser parser = new JSONParser();
        InputStream inputStream = null;
        JSONObject jsonObject = null;
        {
            try {
                inputStream = getClass().getClassLoader().getResourceAsStream("viewCartResponse.json");
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


        return builder
                .given("")
                .uponReceiving("View Cart Pact")
                .path("/cart")
                .headers(headers)
                .method("GET")
                .willRespondWith()
                .headers(headers2)
                .status(200)
                .body(jsonObject)
                .toPact();
                   /*     .eachLike("addressList")
                         .stringType("addressGroupId")
                         .stringType("addressId")
                         .stringType("addressLine1")
                         .stringType("addressLine2")
                                .stringType("addressType")
                                .stringType("city")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")
                                .stringType("addressGroupId")


  "addressList": [
                    {
                        "addressGroupId": "string",
                            "addressId": "string",
                            "addressLine1": "string",
                            "addressLine2": "string",
                            "addressType": "RESIDENTIAL",
                            "city": "string",
                            "companyName": "string",
                            "country": "string",
                            "email": "string",
                            "extn": "string",
                            "firstName": "string",
                            "isValid": true,
                            "lastName": "string",
                            "latitude": "string",
                            "longitude": "string",
                            "phone": "string",
                            "state": "string",
                            "zipCode": "string"
                    }
  ],
          "attributeName": "string",
          "attributeType": "string",
          "attributeValue": "string",
          "billingAddress": {
                    "addressGroupId": "string",
                            "addressId": "string",
                            "addressLine1": "string",
                            "addressLine2": "string",
                            "addressType": "RESIDENTIAL",
                            "city": "string",
                            "companyName": "string",
                            "country": "string",
                            "email": "string",
                            "extn": "string",
                            "firstName": "string",
                            "isValid": true,
                            "lastName": "string",
                            "latitude": "string",
                            "longitude": "string",
                            "phone": "string",
                            "state": "string",
                            "zipCode": "string"
                },
                        "businessChannel": "DIGITAL_LOWESDESKTOP",
                        "cartComments": "string",
                        "cartId": "string",
                        "cartItems": [
                    {
                        "additionalServices": {
                        "additionalProp1": [
                        {
                            "additionalServiceType": "ASSEMBLY",
                                "cartItemId": "string",
                                "description": "string",
                                "eppMonths": 0,
                                "eppPerMonthCost": 0,
                                "eppTermsInYears": 0,
                                "itemNumber": "string",
                                "itemSubTotal": 0,
                                "itemSummary": {
                            "discount": 0,
                                    "fees": [
                            {
                                "amount": 0,
                                    "description": "string",
                                    "type": "NC_WHITE_GOODS"
                            }
              ],
                            "labourFee": 0,
                                    "leadFee": 0,
                                    "markDownSaving": 0,
                                    "mileageFee": 0,
                                    "salesTax": 0,
                                    "shippingCharge": 0,
                                    "shippingTax": 0,
                                    "subTotal": 0,
                                    "totalPrice": 0
                        },
                            "name": "string",
                                "omniItemId": "string",
                                "priceInfo": {
                            "priceType": 0,
                                    "prices": [
                            {
                                "type": "RETAIL",
                                    "value": 0
                            }
              ],
                            "saveComponents": [
                            {
                                "percentage": 0,
                                    "type": "WASNOW"
                            }
              ]
                        },
                            "productDetailURL": "string",
                                "quantity": 0,
                                "recommended": true,
                                "recommendedPlan": true,
                                "selected": true,
                                "warrantyType": "string"
                        }
        ],
                        "additionalProp2": [
                        {
                            "additionalServiceType": "ASSEMBLY",
                                "cartItemId": "string",
                                "description": "string",
                                "eppMonths": 0,
                                "eppPerMonthCost": 0,
                                "eppTermsInYears": 0,
                                "itemNumber": "string",
                                "itemSubTotal": 0,
                                "itemSummary": {
                            "discount": 0,
                                    "fees": [
                            {
                                "amount": 0,
                                    "description": "string",
                                    "type": "NC_WHITE_GOODS"
                            }
              ],
                            "labourFee": 0,
                                    "leadFee": 0,
                                    "markDownSaving": 0,
                                    "mileageFee": 0,
                                    "salesTax": 0,
                                    "shippingCharge": 0,
                                    "shippingTax": 0,
                                    "subTotal": 0,
                                    "totalPrice": 0
                        },
                            "name": "string",
                                "omniItemId": "string",
                                "priceInfo": {
                            "priceType": 0,
                                    "prices": [
                            {
                                "type": "RETAIL",
                                    "value": 0
                            }
              ],
                            "saveComponents": [
                            {
                                "percentage": 0,
                                    "type": "WASNOW"
                            }
              ]
                        },
                            "productDetailURL": "string",
                                "quantity": 0,
                                "recommended": true,
                                "recommendedPlan": true,
                                "selected": true,
                                "warrantyType": "string"
                        }
        ],
                        "additionalProp3": [
                        {
                            "additionalServiceType": "ASSEMBLY",
                                "cartItemId": "string",
                                "description": "string",
                                "eppMonths": 0,
                                "eppPerMonthCost": 0,
                                "eppTermsInYears": 0,
                                "itemNumber": "string",
                                "itemSubTotal": 0,
                                "itemSummary": {
                            "discount": 0,
                                    "fees": [
                            {
                                "amount": 0,
                                    "description": "string",
                                    "type": "NC_WHITE_GOODS"
                            }
              ],
                            "labourFee": 0,
                                    "leadFee": 0,
                                    "markDownSaving": 0,
                                    "mileageFee": 0,
                                    "salesTax": 0,
                                    "shippingCharge": 0,
                                    "shippingTax": 0,
                                    "subTotal": 0,
                                    "totalPrice": 0
                        },
                            "name": "string",
                                "omniItemId": "string",
                                "priceInfo": {
                            "priceType": 0,
                                    "prices": [
                            {
                                "type": "RETAIL",
                                    "value": 0
                            }
              ],
                            "saveComponents": [
                            {
                                "percentage": 0,
                                    "type": "WASNOW"
                            }
              ]
                        },
                            "productDetailURL": "string",
                                "quantity": 0,
                                "recommended": true,
                                "recommendedPlan": true,
                                "selected": true,
                                "warrantyType": "string"
                        }
        ]
                    },
                        "alerts": [
                        {
                            "code": "string",
                                "message": "string",
                                "messageCode": "string",
                                "type": "ERROR"
                        }
      ],
                        "cartItemId": "string",
                            "createdTime": "2020-01-23T12:44:13.745Z",
                            "deliveryInstruction": "string",
                            "eyebrow": "string",
                            "freeDetail": true,
                            "freeLead": true,
                            "freeMileage": true,
                            "freightCharge": 0,
                            "fulfillments": [
                        {
                            "addressGroupId": "string",
                                "availabilityCount": 0,
                                "availabilityInDays": 0,
                                "availabilityTime": "string",
                                "availabilityType": "IN_STOCK",
                                "availableQty": 0,
                                "availableStatus": true,
                                "deliveryDate": "2020-01-23T12:44:13.745Z",
                                "deliverySlotId": "string",
                                "fulfillmentId": "string",
                                "fulfillmentNode": "string",
                                "fulfillmentType": "TRUCK",
                                "selected": true,
                                "shippingOptionCode": 0,
                                "shippingOptionGroupId": "string",
                                "shippingOptions": [
                            {
                                "available": true,
                                    "selected": true,
                                    "shippingCharge": 0,
                                    "shippingOptionType": "STANDARD"
                            }
          ]
                        }
      ],
                        "gcMessage": "string",
                            "gcRecipient": "string",
                            "gcSender": "string",
                            "isFreeGift": true,
                            "isRequiredToFunction": true,
                            "itemStockType": "SOS",
                            "itemSummary": {
                        "discount": 0,
                                "fees": [
                        {
                            "amount": 0,
                                "description": "string",
                                "type": "NC_WHITE_GOODS"
                        }
        ],
                        "labourFee": 0,
                                "leadFee": 0,
                                "markDownSaving": 0,
                                "mileageFee": 0,
                                "salesTax": 0,
                                "shippingCharge": 0,
                                "shippingTax": 0,
                                "subTotal": 0,
                                "totalPrice": 0
                    },
                        "maxQty": 0,
                            "meta": {
                        "alertStatus": [
                        "SUCCESS"
        ],
                        "alerts": [
                        {
                            "code": "string",
                                "message": "string",
                                "messageCode": "string",
                                "type": "ERROR"
                        }
        ],
                        "status": 0,
                                "timestamp": "2020-01-23T12:44:13.745Z",
                                "traceId": "string"
                    },
                        "minQty": 0,
                            "mulQty": 0,
                            "parentCartItemId": "string",
                            "priceInfo": {
                        "priceType": 0,
                                "prices": [
                        {
                            "type": "RETAIL",
                                "value": 0
                        }
        ],
                        "saveComponents": [
                        {
                            "percentage": 0,
                                "type": "WASNOW"
                        }
        ]
                    },
                        "productInfo": {
                        "hazmatCode": "string",
                                "imageUrl": "string",
                                "itemNumber": "string",
                                "itemType": "DUMMY",
                                "labourItemGroupCode": "string",
                                "model": "string",
                                "omniItemId": "string",
                                "pdUrl": "string",
                                "productDescription": "string",
                                "sosFreightType": "PRE_PAID",
                                "vendor": "string",
                                "weight": 0
                    },
                        "promotions": [
                        {
                            "amountSaved": 0,
                                "discount": 0,
                                "longDescription": "string",
                                "promoCode": "string",
                                "promotionGroupCode": "string",
                                "promotionId": "string",
                                "promotionName": "string",
                                "promotionType": "string",
                                "shortDescription": "string"
                        }
      ],
                        "quantity": 0,
                            "recommended": true,
                            "shippingAddress": {
                        "addressGroupId": "string",
                                "addressId": "string",
                                "addressLine1": "string",
                                "addressLine2": "string",
                                "addressType": "RESIDENTIAL",
                                "city": "string",
                                "companyName": "string",
                                "country": "string",
                                "email": "string",
                                "extn": "string",
                                "firstName": "string",
                                "isValid": true,
                                "lastName": "string",
                                "latitude": "string",
                                "longitude": "string",
                                "phone": "string",
                                "state": "string",
                                "zipCode": "string"
                    },
                        "title": "string"
                    }
  ],
          "cartName": "string",
          "cartSummary": {
                    "fees": [
                    {
                        "amount": 0,
                            "description": "string",
                            "type": "NC_WHITE_GOODS"
                    }
    ],
                    "freeShippingPromoProximity": 0,
                            "freeShippingThreshold": 0,
                            "grandTotal": 0,
                            "mileageFee": 0,
                            "remainingOrderBalance": 0,
                            "subTotal": 0,
                            "totalFees": 0,
                            "totalMarkDownSaving": 0,
                            "totalParcelCharge": 0,
                            "totalSalesTax": 0,
                            "totalSaving": 0,
                            "totalShippingCharge": 0,
                            "totalShippingTax": 0,
                            "totalTruckDeliveryCharge": 0
                },
                        "cartType": "SHOPPING",
                        "contactInformation": {
                    "email": "string",
                            "phone": "string",
                            "preferredContact": "string"
                },
                        "coupons": [
                    {
                        "couponCode": "string",
                            "isApplicable": true,
                            "isValid": true,
                            "promotionId": "string"
                    }
  ],
          "currency": "USD",
          "deliveryInstruction": "string",
          "detailFeeAddress": {
                    "addressGroupId": "string",
                            "addressId": "string",
                            "addressLine1": "string",
                            "addressLine2": "string",
                            "addressType": "RESIDENTIAL",
                            "city": "string",
                            "companyName": "string",
                            "country": "string",
                            "email": "string",
                            "extn": "string",
                            "firstName": "string",
                            "isValid": true,
                            "lastName": "string",
                            "latitude": "string",
                            "longitude": "string",
                            "phone": "string",
                            "state": "string",
                            "zipCode": "string"
                },
                        "detailFeeQuestionnaire": [
                    {
                        "id": "string",
                            "key": "string",
                            "type": "string",
                            "value": "string"
                    }
  ],
          "detailFeeScheduleDetails": {
                    "installerId": "string",
                            "reservationId": "string",
                            "slotEndTime": "string",
                            "slotId": "string",
                            "slotStartTime": "string"
                },
                        "detailFeeTnCAccepted": true,
                        "detailId": "string",
                        "fraudCheckStatus": "COMPLETE",
                        "freeShippingEligibilityThreshold": 0,
                        "freeShippingProximity": 0,
                        "initiatedCheckout": true,
                        "itemCount": 0,
                        "laborCategoryDesc": "string",
                        "leadFeeCharge": 0,
                        "lowesCardOption": [
                    {
                        "annualPercentRate": 0,
                            "aprModel": {
                        "aprIndicator": "string",
                                "errors": [
                        "string"
        ],
                        "gotoApr": "string",
                                "success": "string"
                    },
                        "identifier": "string",
                            "lowesCardOptionName": "string",
                            "months": "string",
                            "promoGroupCode": "string",
                            "promoPercent": "string",
                            "promoType": "string",
                            "selected": true,
                            "status": "string",
                            "termId": "string",
                            "termType": "string",
                            "thresholdAmount": 0,
                            "tnCRequired": true,
                            "typeId": "string"
                    }
  ],
          "meta": {
                    "alertStatus": [
                    "SUCCESS"
    ],
                    "alerts": [
                    {
                        "code": "string",
                            "message": "string",
                            "messageCode": "string",
                            "type": "ERROR"
                    }
    ],
                    "status": 0,
                            "timestamp": "2020-01-23T12:44:13.745Z",
                            "traceId": "string"
                },
                        "mileageChargeThreshold": "string",
                        "mileageFeeCharge": 0,
                        "orderId": "string",
                        "payments": [
                    {
                        "accountType": "CREDIT",
                            "amount": 0,
                            "attachedTime": "2020-01-23T12:44:13.745Z",
                            "cardToken": "string",
                            "cardType": "MASTERCARD",
                            "cvv": "string",
                            "debitPinBlock": "string",
                            "deviceFingerPrint": "string",
                            "displayText": "string",
                            "emvTags": "string",
                            "etbPinBlock": "string",
                            "expirationMonth": 0,
                            "expirationYear": 0,
                            "gcBalance": 0,
                            "giftCardNumber": "string",
                            "meta": {
                        "alertStatus": [
                        "SUCCESS"
        ],
                        "alerts": [
                        {
                            "code": "string",
                                "message": "string",
                                "messageCode": "string",
                                "type": "ERROR"
                        }
        ],
                        "status": 0,
                                "timestamp": "2020-01-23T12:44:13.745Z",
                                "traceId": "string"
                    },
                        "operatorId": "string",
                            "paymentId": "string",
                            "pin": "string",
                            "track1Data": "string",
                            "track2Data": "string",
                            "transactionType": "VISACHECKOUT"
                    }
  ],
          "poNumber": "string",
          "projectDesc": "string",
          "projectId": "string",
          "promotions": [
                    {
                        "amountSaved": 0,
                            "discount": 0,
                            "longDescription": "string",
                            "promoCode": "string",
                            "promotionGroupCode": "string",
                            "promotionId": "string",
                            "promotionName": "string",
                            "promotionType": "string",
                            "shortDescription": "string"
                    }
  ],
          "selectedFinanceOption": "string",
          "sourceId": "string",
          "sstiTnCAccepted": true,
          "subscribeToPromotion": true,
          "taxToggle": true,
          "tcAccepted": true,
          "tenantType": "LOWES",
          "testOrder": true,
          "useAsBillingAddress": "string",
          "zippedInStore": {
                    "address": {
                        "addressGroupId": "string",
                                "addressId": "string",
                                "addressLine1": "string",
                                "addressLine2": "string",
                                "addressType": "RESIDENTIAL",
                                "city": "string",
                                "companyName": "string",
                                "country": "string",
                                "email": "string",
                                "extn": "string",
                                "firstName": "string",
                                "isValid": true,
                                "lastName": "string",
                                "latitude": "string",
                                "longitude": "string",
                                "phone": "string",
                                "state": "string",
                                "zipCode": "string"
                    },
                    "name": "string",
                            "storeId": "string"
                }
                }
                              .stringType())*/

    }

    @Override
    protected String providerName() {
        return "ExampleProvider";
    }

    @Override
    protected String consumerName() {
        return "BaseConsumer";
    }

    @Override
    protected void runTest(MockServer mockServer, PactTestExecutionContext pactTestExecutionContext) throws IOException {
        System.out.println(mockServer.getUrl());
        RestTemplate restTemplate = new RestTemplate();
        Cart cart = restTemplate.getForObject("http://localhost:6060/cart",  Cart.class);
        assertNotNull(cart.getCartId());
        /*providerService.setBackendURL(mockServer.getUrl());
        Information information = providerService.getInformation();
        assertEquals(information.getName(), "Hatsune Miku");*/
    }
}
