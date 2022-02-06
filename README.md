# Hakalle

Hakalle is a bot originally developed by [Velosh](https://t.me/Velosh), in order to replace the original [Bot³+t](https://github.com/VegaBobo/Bot3).

## Notice
- Remember that this bot is licensed under the GPLv3 license.
- This source may contain bugs or silly problems, if you find one: Don't hesitate to report it! I don't have my computer for now, I fixed some problems on my own phone, and tested it with Termux.

## What are the reasons?
- Having a modular bot that is easy to add commands, modify, and bring in new functions is good.
- Being able to add different stages for building GSI (and SGSI) for ErfanGSIs and Xiaoxindada (coming soon).
- Easy to handle, and easy to use.
- Simple, basic, and just enough to do your job.

## Necessary requirements to run/build
- JDK 17 (Preferably GraalVM JDK 17)
- Machine Linux: Aarch64 (Rasp. PI+, Android) or x86_64 (Desktop)
- Brain, patience, minimum and basic knowledge about Linux and Java.

## How to setup

 - To build the source:
    ```$ ./gradlew shadowJar```

 - Fill in `hakalle.yaml` file:
    ```
    token: Your telegram bot's token
    username: Your telegram bot's username (Without @)
    creatorId: Your telegram's user id
    sfProject: Your SourceForge's project which will be used to upload GSI images
    sfUsername: Your SourceForge's user
    sfPassword: Your SourceForge's account password
    privateChatId: Channel/Group id to receive orders (requests)
    publicChatId: Group id to send orders
    channelId: Your channel/group to send GSI post
    channel: Your channel/group public username to send GSI post
    ```

 - How to run the bot:
    ```$ java -jar hakalle/build/libs/hakalle-0.1-all.jar```

## Credits
- [VegaZS](https://github.com/VegaBobo)