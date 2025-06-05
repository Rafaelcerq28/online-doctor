package com.medicine.online_doctor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.model.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
public class PaymentController {
    
        public void StripeConfirmPaymentController(@Value("${stripe.api.key}") String secretKey) {
            Stripe.apiKey = secretKey;
        }
        @PostMapping("/payment")
        public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentRequest request) throws StripeException {
        
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                // apagar depois pois isso nao parece ser o correto
                .setConfirm(true) // apagar depois pois isso nao paree ser o correto
                .setPaymentMethod("pm_card_visa")// Cartão de teste da Stripe
                // fim doapagar depois pois isso nao parece ser o correto
                .setReturnUrl("https://seusite.com/pagamento/concluido")
                .setAmount(request.getAmount()) // em centavos: R$10,00 = 1000
                .setCurrency("eur")
                .setDescription("Consulta médica com o Dr. Fulano")
                .setReceiptEmail(request.getEmail())
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("clientSecret", intent.getClientSecret());

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/payment/confirm/{paymentIntentId}")
    public ResponseEntity<?> confirmPayment(@PathVariable String paymentIntentId) {
        try {
            PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);

            Map<String, Object> params = new HashMap<>();
            params.put("payment_method", "pm_card_visa"); // Cartão de teste da Stripe

            PaymentIntent confirmedIntent = intent.confirm(params);

            Map<String, Object> response = new HashMap<>();
            response.put("status", confirmedIntent.getStatus());
            response.put("paymentIntentId", confirmedIntent.getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao confirmar pagamento: " + e.getMessage());
        }
    }

}
