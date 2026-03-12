import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataBaseTest {
    private DataBase db;
    @BeforeEach
    void init() {
        db = new DataBase();
    }
    @Test
    @DisplayName("SET 후 GET으로 값을 조회할 수 있다")
    void setAndGet() {
        db.set("name", "gabi");
        assertThat(db.get("name")).isEqualTo("gabi");
    }

    @Test
    @DisplayName("없는 키로 조회하면 예외가 발생해야한다")
    void getNonExistentKey() {
        assertThatThrownBy(() -> db.get("hello"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("동일한 키가 존재하면 덮어쓴다")
    void setOverwrite() {
        db.set("number", "100");
        db.set("number", "500");

        assertThat(db.get("number")).isEqualTo("500");
    }


    @Test
    @DisplayName("키를 삭제한다")
    void delete() {
        db.set("name", "gabi");
        db.delete("name");

        assertThatThrownBy(() -> db.get("name"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("없는 키를 삭제하면 예외가 발생한다")
    void deleteNonExistentKey() {
        assertThatThrownBy(() -> db.delete("hello"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Keys는 저장된 모든 key를 반환한다")
    void getKeys() {
        Set<String> keys = Set.of("key1", "key2", "key3", "key10");
        for (String key : keys) {
            db.set(key, "value");
        }

        assertThat(db.getKeys().split(" ")).containsExactlyInAnyOrderElementsOf(keys);
    }

}