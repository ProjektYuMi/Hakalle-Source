/*
    Project YuMi
    Copyright (C) 2022 Hakalle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package xyz.br.velosh.projektyumi.hakalle.commands.gsi;

import static xyz.br.velosh.projektyumi.hakalle.bot.Hakalle.botConfig;

import com.annimon.tgbotsmodule.api.methods.answerqueries.AnswerCallbackQueryMethod;
import com.annimon.tgbotsmodule.api.methods.send.SendMessageMethod;
import com.annimon.tgbotsmodule.api.methods.updatingmessages.DeleteMessageMethod;
import com.annimon.tgbotsmodule.commands.CommandBundle;
import com.annimon.tgbotsmodule.commands.CommandRegistry;
import com.annimon.tgbotsmodule.commands.SimpleCallbackQueryCommand;
import com.annimon.tgbotsmodule.commands.SimpleCommand;
import com.annimon.tgbotsmodule.commands.authority.For;
import com.annimon.tgbotsmodule.commands.context.MessageContext;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Velosh <daffetyxd@gmail.com>
 * This class was programmed to fulfill all requests sent from the public group to the private group.
 */
public class Order implements CommandBundle<For> {
    @Override
    public void register(@NotNull CommandRegistry<For> registry) {
        registry.splitCallbackCommandByColon();
        registry.register(new SimpleCallbackQueryCommand("accept", callbackQueryContext -> {
            // User which who asked to accept user's order.
            final String acceptOrderUser = String.valueOf(callbackQueryContext.user().getId());

            // Check if the denyOrderUser is the creator.
            if (!acceptOrderUser.equals(botConfig.getCreatorId().toString())) {
                callbackQueryContext.answerAsAlert("You're not authorized to do this action.")
                        .callAsync(callbackQueryContext.sender);
                return;
            }

            // Get the only callback info passed by the accept command.
            final var callbackInfo = callbackQueryContext.argumentsAsString();
            String userID;
            String userName;
            try {
                userID = callbackInfo.split(":")[0];
                userName = callbackInfo.split(":")[1];
            } catch (Exception exception) {
                callbackQueryContext.answerAsAlert("There was a problem trying to deny the request! Can't fetch user info.").callAsync(callbackQueryContext.sender);
                return;
            }

            // Check if the variables still null.
            if (userID == null
                    || userName == null) {
                callbackQueryContext.answerAsAlert("There was a problem trying to deny the request! His/her username or userid is null.").callAsync(callbackQueryContext.sender);
                return;
            }

            // Alert the user about the accepted request.
            final var MessageString = "Hello <a href=\"tg://user?id=%2\">%1</a> (<code>%3</code>), your request has been accepted! For the request to be made and sent to the channel it may take a while (or maybe not), depending on several factors, like the speed of the server where the file (the firmware/ROM link you sent) is hosted, the region where it is hosted and others.";
            SendMessageMethod sendMessageMethod = new SendMessageMethod();
            sendMessageMethod.setChatId(botConfig.getPublicChatID());
            sendMessageMethod.setText(MessageString
                            .replace("%1", userName)
                            .replace("%2", userID)
                            .replace("%3", userID)).enableHtml(true)
                    .callAsync(callbackQueryContext.sender);

            // Answer the queryId.
            AnswerCallbackQueryMethod answerCallbackQueryMethod = new AnswerCallbackQueryMethod();
            answerCallbackQueryMethod
                    .setCallbackQueryId(callbackQueryContext.queryId())
                    .callAsync(callbackQueryContext.sender);
        }));

        // Method specialized to deny user's order.
        registry.register(new SimpleCallbackQueryCommand("deny", callbackQueryContext -> {
            // User which who asked to deny user's order.
            final String denyOrderUser = String.valueOf(callbackQueryContext.user().getId());

            // Check if the denyOrderUser is the creator.
            if (!denyOrderUser.equals(botConfig.getCreatorId().toString())) {
                callbackQueryContext.answerAsAlert("You're not authorized to do this action.")
                        .callAsync(callbackQueryContext.sender);
                return;
            }

            // Get the only callback info passed by the cancel command.
            final var callbackInfo = callbackQueryContext.argumentsAsString();
            String userID;
            String userName;
            try {
                userID = callbackInfo.split(":")[0];
                userName = callbackInfo.split(":")[1];
            } catch (Exception exception) {
                callbackQueryContext.answerAsAlert("There was a problem trying to deny the request! Can't fetch user info.").callAsync(callbackQueryContext.sender);
                return;
            }

            // Check if the variables still null.
            if (userID == null
                    || userName == null) {
                callbackQueryContext.answerAsAlert("There was a problem trying to deny the request! His/her username or userid is null.").callAsync(callbackQueryContext.sender);
                return;
            }

            // Delete the order message.
            DeleteMessageMethod deleteMessageMethod = new DeleteMessageMethod();
            deleteMessageMethod.setChatId(callbackQueryContext.message().getChatId());
            deleteMessageMethod.setMessageId(callbackQueryContext.message().getMessageId());
            deleteMessageMethod.callAsync(callbackQueryContext.sender);

            // Alert the user about the denied request.
            final var MessageString = "Hello <a href=\"tg://user?id=%2\">%1</a> (<code>%3</code>), your request has been declined. Please review why this has happened if it is not a violation or problem with your request.";
            SendMessageMethod sendMessageMethod = new SendMessageMethod();
            sendMessageMethod.setChatId(botConfig.getPublicChatID());
            sendMessageMethod.setText(MessageString
                            .replace("%1", userName)
                            .replace("%2", userID)
                            .replace("%3", userID)).enableHtml(true)
                    .callAsync(callbackQueryContext.sender);

            // Answer the queryId.
            AnswerCallbackQueryMethod answerCallbackQueryMethod = new AnswerCallbackQueryMethod();
            answerCallbackQueryMethod
                    .setCallbackQueryId(callbackQueryContext.queryId())
                    .callAsync(callbackQueryContext.sender);
        }));
        registry.register(new SimpleCommand("/order", this::orderGSI));
        registry.register(new SimpleCommand("/request", this::orderGSI));
    }

    private void orderGSI(MessageContext messageContext) {
        // Check if there's an issue with private/public configs.
        if (botConfig.getPrivateChatId() == null
                || (botConfig.getPrivateChatId().toString().isEmpty() || botConfig.getPrivateChatId().toString().isBlank())) {
            messageContext.replyToMessage("Can't send request, the private chat variable doesn't exist or has no value. Contact the developer.")
                    .callAsync(messageContext.sender);
            return;
        } else if (botConfig.getPublicChatID() == null
                || (botConfig.getPublicChatID().toString().isEmpty() || botConfig.getPublicChatID().toString().isBlank())) {
            messageContext.replyToMessage("Can't send request, the public chat variable doesn't exist or has no value. Contact the developer.")
                    .callAsync(messageContext.sender);
            return;
        } else if (!botConfig.getPrivateChatId().toString().startsWith("-100")
                || !botConfig.getPublicChatID().toString().startsWith("-100")) {
            messageContext.replyToMessage("The chat (public and private) variables must be used and implemented in the form of Bot API, check if both is correct. Remembering that both must be supergroups.")
                    .callAsync(messageContext.sender);
            return;
        }

        // Check if this chat is the YuMi's group.
        if (!messageContext.chatId().equals(botConfig.getPublicChatID())) {
            messageContext.replyToMessage("This bot can't be used in other locations! Go to this <a href=\"https://t.me/ProjektYuMiChat\">group</a> instead.")
                    .disableWebPagePreview()
                    .enableHtml(true)
                    .callAsync(messageContext.sender);
            return;
        }

        // Ignore Telegram user ID.
        // If the message doesn't enough content to consider as args, also ignore & delete.
        if (messageContext.message().getFrom().getId() == Float.parseFloat(String.valueOf(777000))
                || messageContext.message().getText().length() <= 8) {
            messageContext.deleteMessage().callAsync(messageContext.sender);
            return;
        }

        // Setup firstly our arguments.
        String[] msgComparableRaw = messageContext.message().getText().split(" ");

        // If we have enough arguments, initialize other variables.
        String URL = msgComparableRaw[1];
        String regexURL = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        // Check if the URL is valid
        if (Pattern.matches(regexURL, URL)) {
            // Get the additional info by user.
            var addInfo = "";
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 2; i < msgComparableRaw.length; i++)
                    stringBuilder.append(msgComparableRaw[i]).append(" ");

                // Need to say something?
                addInfo = String.valueOf(stringBuilder);
                addInfo = addInfo.substring(0, addInfo.length() - 1);
            } catch (Exception exception) {
                addInfo = "Information not shared by the user.";
            }

            // Set up our Keyboard Markup.
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            // Set up our Keyboard Button list for listing.
            List<List<InlineKeyboardButton>> buttonsList = new ArrayList<>();

            // Set up our Keyboard Button list.
            List<InlineKeyboardButton> firmwareButtonList = new ArrayList<>();
            List<InlineKeyboardButton> optionsButtonsList = new ArrayList<>();

            // Create accept button.
            InlineKeyboardButton firmwareButton = new InlineKeyboardButton();
            firmwareButton.setText("\uD83D\uDCE6 Firmware or ROM Download");
            firmwareButton.setUrl(msgComparableRaw[1]);

            // Create accept button.
            InlineKeyboardButton acceptButton = new InlineKeyboardButton();
            acceptButton.setText("✅ Accept the order");
            acceptButton.setCallbackData("accept:" + messageContext.message().getFrom().getId() + ":" + messageContext.message().getFrom().getFirstName());

            // Create decline button.
            InlineKeyboardButton declineButton = new InlineKeyboardButton();
            declineButton.setText("❌ Decline");
            declineButton.setCallbackData("deny:" + messageContext.message().getFrom().getId() + ":" + messageContext.message().getFrom().getFirstName());

            // Add both buttons into Keyboard Button List listing.
            firmwareButtonList.add(firmwareButton);
            optionsButtonsList.add(acceptButton);
            optionsButtonsList.add(declineButton);

            // Add the only list of rows inline buttons into markup.
            buttonsList.add(firmwareButtonList);
            buttonsList.add(optionsButtonsList);

            // Define our only list of rows inline buttons to the keyboard markup.
            inlineKeyboardMarkup.setKeyboard(buttonsList);

            // Now send the message.
            messageContext.reply(
                    "\uD83D\uDCE9 <b>New GSI order received!</b>" + "\n\n"
                    + "❕ <b>Additional information</b>" + "\n"
                    + "<code>" + addInfo + "</code>" + "\n\n"
                    + "❔ <b>User info</b>" + "\n\n"
                    + "<b>• First name:</b> <code>" + messageContext.message().getFrom().getFirstName() + "</code>" + "\n"
                    + "<b>• Username:</b> <code>" + validateUsername(messageContext.message().getFrom().getUserName()) + "</code>" + "\n"
                    + "<b>• User ID:</b> <code>" + messageContext.message().getFrom().getId() + "</code>"
                    )
                    .setReplyMarkup(inlineKeyboardMarkup)
                    .setChatId(botConfig.getPrivateChatId())
                    .enableHtml(true)
                    .callAsync(messageContext.sender);

            // Ah.
            String messageReply = "Hello <a href=\"tg://user?id=%2\">%1</a> (<code>%3</code>), your request has been sent! Just wait now.";
            messageContext.reply(messageReply
                            .replace("%1", messageContext.message().getFrom().getFirstName())
                            .replace("%2", String.valueOf(messageContext.message().getFrom().getId()))
                            .replace("%3", String.valueOf(messageContext.message().getFrom().getId()))
                    ).enableHtml(true)
                    .callAsync(messageContext.sender);
            messageContext.deleteMessage().callAsync(messageContext.sender);
        } else {
            String messageReply = "Hello <a href=\"tg://user?id=%2\">%1</a> (<code>%3</code>), the link you sent appears to be invalid! Use with http parameters and try again.";
            messageContext.reply(messageReply
                            .replace("%1", messageContext.message().getFrom().getFirstName())
                            .replace("%2", String.valueOf(messageContext.message().getFrom().getId()))
                            .replace("%3", String.valueOf(messageContext.message().getFrom().getId()))
                    ).enableHtml(true)
                    .callAsync(messageContext.sender);
        }
    }

    private String validateUsername(String username) {
        if (username == null || username.equals("")) {
            return "This has no user link.";
        } else {
            return "@" + username.replace("@", "");
        }
    }
}