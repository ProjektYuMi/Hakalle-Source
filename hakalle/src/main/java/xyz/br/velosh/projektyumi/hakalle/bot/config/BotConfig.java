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
package xyz.br.velosh.projektyumi.hakalle.bot.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Hakalle <xdhakalle@gmail.com>
 */
public class BotConfig {

    @JsonProperty(required = true)
    private String token;

    @JsonProperty(required = true)
    private String username;
    
    @JsonProperty(required = true)
    private String sfUsername;
    
    @JsonProperty(required = true)
    private String sfPassword;
    
    @JsonProperty(required = true)
    private String sfProject;

    @JsonProperty(required = true)
    private Long creatorId;

    @JsonProperty(required = true)
    private Long privateChatId;

    @JsonProperty(required = true)
    private Long publicChatId;

    @JsonProperty(required = true)
    private Long channelId;
    
    @JsonProperty(required = true)
    private String channel;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
    
    public String getSourceForgeUsername() {
        return sfUsername;
    }
    
    public String getSourceForgePassword() {
        return sfPassword;
    }
    
    public String getSourceForgeProject() {
        return sfProject;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getPublicChatID() {
        return publicChatId;
    }

    public Long getPrivateChatId() {
        return privateChatId;
    }

    public Long getChannelId() {
        return channelId;
    }
    
    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "BotConfig{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", sfUsername=" + sfUsername +
                ", sfPassword=" + sfPassword +
                ", sfProject=" + sfProject +
                ", creatorId=" + creatorId +
                ", privateChatId=" + privateChatId +
                ", publicChatId=" + publicChatId +
                ", channelId=" + channelId +
                ", channel=" + channel +
                '}';
    }
}
