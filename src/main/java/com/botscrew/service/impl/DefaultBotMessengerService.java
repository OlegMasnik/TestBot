package com.botscrew.service.impl;

import com.botscrew.data.webhook.FbFromButtonDto;
import com.botscrew.data.webhook.FbRecipientDto;
import com.botscrew.data.webhook.FbRequestDto;
import com.botscrew.data.webhook.subclasses.*;
import com.botscrew.model.FbUser;
import com.botscrew.service.AddressService;
import com.botscrew.service.BotMessengerService;
import com.botscrew.service.MessageService;
import com.botscrew.service.UserService;
import com.botscrew.util.MyRestTemplate;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DefaultBotMessengerService implements BotMessengerService {

    private static final String NOT_UNDERSTAND = "I don't understand you";
    private static final String ADDRESS_CORRECT = "ADDRESS_CORRECT";
    private static final String ADDRESS_INVALID = "ADDRESS_INVALID";

    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    private FbUser curUser;

    @Override
    public String verify(String challenge, String facebookToken) {
        System.out.println("***************** Verification ******************");
        if (FB_MESSENGER_BOT_VERIFY_TOKEN != null) {
            if (FB_MESSENGER_BOT_VERIFY_TOKEN.equals(facebookToken)) {
                return challenge;
            }
        }
        return "failed";
    }

    @Override
    public String sentToMessenger(FbRequestDto botRequest) {
        final FbReqEntry entry = botRequest.getEntry().get(0);
        final String curId = getId(entry);
        curUser = userService.getByIdOrCreateFromFacebook(curId);
        final FbReqEntryMessaging mess = entry.getMessaging().get(0);
        if (mess == null) {
            return "failed";
        }
        messageService.save(mess, curUser, entry.getId().toString());
        if (mess.getPostback() != null) {
            sendAnswer(entry);
        } else if (mess.getMessage() != null && mess.getMessage().getIsEcho() == null) {
            sendAddress(entry.getMessaging().get(0));
        }
        return "ok";
    }

    private String getId(FbReqEntry entry) {
        final String sender = entry.getMessaging().get(0).getSender().getId();
        final String recipient = entry.getMessaging().get(0).getRecipient().getId().toString();
        return entry.getId().toString().equals(sender) ? recipient : sender;
    }

    private void sendAnswer(FbReqEntry entry) {

        final FbReqEntryMessaging messaging = entry.getMessaging().get(0);
        try {
            final FbRecipientDto recipient = initRecipient(messaging.getSender());
            String text;
            if (curUser.getIsActive()) {
                userService.disable(curUser);
                text = ADDRESS_CORRECT.equals(messaging.getPostback().getPayload()) ? "Great :)" : "Sorry :(";
            } else {
                text = "Sorry, this message isn't active";
            }
            setOnlyText(recipient, text);
            sendResponse(recipient);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void sendAddress(FbReqEntryMessaging messaging) {
        try {
            final FbReqEntryMessagingMess message = messaging.getMessage();
            final FbRecipientDto recipient = initRecipient(messaging.getSender());
            final String text = message.getText();
            if (text == null || "".equals(text) || text.length() > 1800) {
                setOnlyText(recipient, NOT_UNDERSTAND);
            } else {
                final String address = addressService.parseAddress(addressService.getAddresses(text));
                if (AddressService.INVALID_ADDRESS.equals(address) || AddressService.NOT_USA_ADDRESS.equals(address)) {
                    setOnlyText(recipient, address);
                } else {
                    setAttachment(recipient, address);
                    userService.enable(curUser);
                }
            }
            sendResponse(recipient);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private FbRecipientDto initRecipient(FbRecipientOrSender recipient) {
        final FbRecipientDto recipientDto = new FbRecipientDto();
        recipientDto.setRecipient(recipient);
        return recipientDto;
    }

    private void sendResponse(final FbRecipientDto recipient) throws URISyntaxException {
        final URIBuilder builder = new URIBuilder(FB_MESSENGER_BOT_ENDPOINT);
        builder.setParameter("access_token", FB_MESSENGER_BOT_ACCESS_TOKEN);
        final HttpPost post = new HttpPost(builder.build());
        post.setHeader("Content-Type", "application/json; charset=UTF-8");
        MyRestTemplate.getRestTemplate().postForObject(post.getURI(), recipient, FbFromButtonDto.class);
    }

    private void setOnlyText(final FbRecipientDto recipient, final String oneString) {
        final FbRecipientMess message = new FbRecipientMess();
        message.setText(oneString);
        recipient.setMessage(message);
    }

    private void setAttachment(final FbRecipientDto recipient, final String oneString) {
        final FbRecipientPayloadBtn button1 = new FbRecipientPayloadBtn();
        button1.setTitle("Yes");
        button1.setPayload(ADDRESS_CORRECT);
        button1.setType("postback");
        final FbRecipientPayloadBtn button2 = new FbRecipientPayloadBtn();
        button2.setTitle("No");
        button2.setPayload(ADDRESS_INVALID);
        button2.setType("postback");
        final FbRecipientPayload payload = new FbRecipientPayload();
        payload.setButtons(new ArrayList<FbRecipientPayloadBtn>(Arrays.asList(button1, button2)));
        payload.setTemplate_type("button");
        payload.setText(oneString + "\nIs it address correct?");
        final FbRecipientAttach attach = new FbRecipientAttach();
        attach.setPayload(payload);
        attach.setType("template");
        final FbRecipientMess message = new FbRecipientMess();
        message.setAttachment(attach);
        recipient.setMessage(message);
    }
}