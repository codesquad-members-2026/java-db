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
        dbManager = new DatabaseManager(new DataBase());
    }

    @Test
    @DisplayName("SET 명령시 값을 저장한다")
    void setAndGet() {
        dbManager.execute("SET name gabi");

        assertThat(dbManager.execute("GET name")).isEqualTo("gabi");
    }

    @Test
    @DisplayName("DELETE 명령시 키를 삭제한다")
    void delete() {
        dbManager.execute("SET name gabi");
        dbManager.execute("DELETE name");

        assertThatThrownBy(() -> dbManager.execute("GET name"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("KEYS는 모든 key를 출력한다")
    void keys() {
        Set<String> keys = Set.of("key1", "key2", "key3", "key10");
        for (String key : keys) {
            dbManager.execute("SET " + key + " value");
        }

        assertThat(dbManager.execute("KEYS").split(" ")).containsExactlyInAnyOrderElementsOf(keys);
    }
}