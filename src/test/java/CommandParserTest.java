import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandParserTest {
    @Test
    @DisplayName("SET 명령어를 파싱한다")
    void parse() {
        Command parse = CommandParser.parse("SET name gabi");

        assertThat(parse.getType()).isEqualTo("SET");
        assertThat(parse.getKey()).isEqualTo("name");
        assertThat(parse.getValue()).isEqualTo("gabi");
    }
}