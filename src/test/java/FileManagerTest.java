import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileManagerTest {
    private FileManager fileManager;

    @BeforeEach
    void init() {
        fileManager = new FileManager("data");
    }

    @Test
    @DisplayName("파일을 읽고 쓸 수 있다.")
    void writeAndRead() {
        Map<String, String> store = new HashMap<>();
        store.put("hello", "world");
        store.put("name", "gabi");
        store.put("age", "25");

        fileManager.writeFile(store);
        Map<String, String> load = fileManager.loadAll();

        assertThat(load).containsExactlyInAnyOrderEntriesOf(store);
    }
}