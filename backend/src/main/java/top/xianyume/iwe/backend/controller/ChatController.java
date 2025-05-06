package top.xianyume.iwe.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Xianyume
 * @date 2025/05/05 18:24
 **/
@Slf4j
@RestController
public class ChatController {

    private final OpenAiChatModel chatModel;

    public ChatController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * 聊天接口
     */
    @GetMapping("/ds/chat")
    public String generate(@RequestParam(value = "message", defaultValue = "hello") String message) {
        return chatModel.call(message);
    }

    /**
     * 流式聊天接口
     */
    @GetMapping("/ds/chatStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "hello") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);
    }

}
