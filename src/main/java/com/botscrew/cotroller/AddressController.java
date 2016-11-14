package com.botscrew.cotroller;

import com.botscrew.data.webhook.FbRequestDto;
import com.botscrew.service.BotMessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    private BotMessengerService botMessengerService;

    @RequestMapping(value = "/fbmessengerbot", method = RequestMethod.GET)
    public String verify(@RequestParam(name = "hub.challenge") String challenge,
                         @RequestParam(name = "hub.verify_token") String facebookToken) {
        System.out.println("************** GET **************");
        return botMessengerService.verify(challenge, facebookToken);
    }

    @RequestMapping(value = "/fbmessengerbot", method = RequestMethod.POST)
    public void message(@RequestBody(required = false) FbRequestDto request) {
        botMessengerService.sentToMessenger(request);
    }
}
