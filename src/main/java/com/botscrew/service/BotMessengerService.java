package com.botscrew.service;

import com.botscrew.data.webhook.FbRequestDto;


public interface BotMessengerService {

    String FB_MESSENGER_BOT_VERIFY_TOKEN = "FB_MESSENGER_BOT_VERIFY_TOKEN";
    String FB_MESSENGER_BOT_ACCESS_TOKEN = "EAACNEZBaI2agBAABK4l3ZA8ZBVwModqx3e4CtIa8ZCm1UMBlhhZARrUjLsIsgrPB4aM4aa1qxRk1jK1ZAYRRC7LNaJvrDtVW4rx6vdmSwQm5dxriZClkCavD9ZBoRK4mMCXnHZAcInGzqCOZAOckP5XElgLQgsptftfNpnkfd8I3aq2gZDZD";
    String FB_MESSENGER_BOT_ENDPOINT = "https://graph.facebook.com/v2.6/me/messages";

    String verify(final String challenge, final String facebookToken);

    String sentToMessenger(final FbRequestDto botRequest);
}
