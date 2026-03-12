import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseManagerTest {
    private DatabaseManager dbManager;

    @BeforeEach
    void init() {
        dbManager = new DatabaseManager();
    }

    @Test
    @DisplayName("SET 명령시 값을 저장한다")
    void setAndGet() {
        dbManager.execute(Command.ofTypeKeyValue("SET", "name", "gabi"));

        assertThat(dbManager.execute(Command.ofTypeAndKey("GET", "name"))).isEqualTo("gabi");
    }

    @Test
    @DisplayName("DELETE 명령시 키를 삭제한다")
    void delete() {

        dbManager.execute(Command.ofTypeKeyValue("SET", "name", "gabi"));
        dbManager.execute(Command.ofTypeAndKey("DELETE", "name"));

        assertThatThrownBy(() -> dbManager.execute(Command.ofTypeAndKey("GET", "name")))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("KEYS는 모든 key를 출력한다")
    void keys() {
        Set<String> keys = Set.of("key1", "key2", "key3", "key10");
        for (String key : keys) {
            dbManager.execute(Command.ofTypeKeyValue("SET", key, "value"));
        }

        assertThat(dbManager.execute(Command.ofType("KEYS")).split(" ")).containsExactlyInAnyOrderElementsOf(keys);
    }


    @Test
    @DisplayName("데이터가 파일에 영속되어 재생성(프로그램 재시작) 후에도 유지된다")
    void saveFile() {
        dbManager.execute(Command.ofTypeKeyValue("SET", "name", "gabi"));
        dbManager.saveFile();

        // 새 인스턴스 - 파일에서 데이터를 다시 불러오는지 검증
        dbManager = new DatabaseManager();

        String getValue = dbManager.execute(Command.ofTypeAndKey("GET", "name"));
        assertThat(getValue).isEqualTo("gabi");
    }
}