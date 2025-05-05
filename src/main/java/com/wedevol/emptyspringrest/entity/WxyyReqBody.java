package com.wedevol.emptyspringrest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxyyReqBody implements Serializable {

    private String model;

    private List<Message> messages;

    private WebSearch web_search = new WebSearch();

    @Data
    public static class Message {
        private String role;

        private String content;

        @Override
        public boolean equals(Object o) {
//            if (this == o) return true;
            if (!(o instanceof Message)) return false;
            Message message = (Message) o;
            return Objects.equals(role, message.role) && Objects.equals(content, message.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(role, content);
        }
    }

    @Data
    public static class WebSearch {
        private Boolean enable = false;
        private Boolean enable_citation = false;
        private Boolean enable_trace = false;


        @Override
        public boolean equals(Object o) {
//            if (this == o) return true;
            if (!(o instanceof WebSearch)) return false;
            WebSearch webSearch = (WebSearch) o;
            return Objects.equals(enable, webSearch.enable) && Objects.equals(enable_citation, webSearch.enable_citation) && Objects.equals(enable_trace, webSearch.enable_trace);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enable, enable_citation, enable_trace);
        }
    }


    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WxyyReqBody that = (WxyyReqBody) o;
        return Objects.equals(model, that.model) && Objects.equals(messages, that.messages) && Objects.equals(web_search, that.web_search);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, messages, web_search);
    }
}
