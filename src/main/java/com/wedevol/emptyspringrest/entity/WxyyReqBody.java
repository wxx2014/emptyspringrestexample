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

    private ResponseFormat response_format;

    /**
     * 较高的数值会使输出更加随机，而较低的数值会使其更加集中和确定(0, 1.0]
     */
    private Double temperature;

    /**
     * 影响输出文本的多样性，取值越大，生成文本的多样性越强 [0, 1.0]
     */
    private Double top_p;

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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseFormat {
        private String type;
        // private Map json_schema;
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
