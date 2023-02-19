package com.bitsmilez.checkoutmicroservice.port.checkoutAdapter;

import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class CheckoutController {
    private final ICheckoutService checkoutService;

    public CheckoutController(ICheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // TODO: GET -> last order (UserID)
    @GetMapping(value = "/checkout/latest/{userID}")
    public ResponseEntity<WebOrderDTO> getLastOrder(@PathVariable(name = "userID") String userID) {
        UUID id = UUID.fromString(userID);
        WebOrderDTO order = checkoutService.getLatestCheckout(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // TODO GET -> All Orders (UserID)


}
