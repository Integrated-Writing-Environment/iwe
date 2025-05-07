package top.xianyume.iwe.backend.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xianyume
 * @date 2025/05/05 17:38
 **/
@SpringBootTest
public class DeepSeekTest {

    private final OpenAiChatModel chatModel = OpenAiChatModel.builder()
            .build();

    @Test
    public void test() {
        String message = "s";
        String s = chatModel.call(message);
        System.out.println(s);
    }
}

