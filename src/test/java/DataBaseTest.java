import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
        String key = "name";
        String value = "gabi";
        db.set(key, value);
        assertThat(db.get(key)).isEqualTo(value);
    }

    @Test
    @DisplayName("없는 키로 조회하면 예외가 발생해야한다")
    void getNonExistentKey() {
        assertThatThrownBy(() -> db.get("hello"))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("동일한 키가 존재하면 덮어쓴다")
    void setOverwrite() {
        String key = "number";
        String value = "100";

        db.set(key, value);

        String newValue = "500";
        db.set(key, newValue);

        assertThat(db.get(key)).isEqualTo(newValue);
    }


}