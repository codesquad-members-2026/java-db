import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileManager {
    private static final int META_DATA_BYTE_SIZE = 4;
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, String> loadAll() {
        Map<String, String> load = new HashMap<>();

        try (BufferedInputStream metaReader = new BufferedInputStream(new FileInputStream(fileName + ".meta"));
             BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(fileName + ".bin"))
        ) {
            byte[] metaDataBytes = metaReader.readAllBytes();
            byte[] fileDataBytes = fileReader.readAllBytes();

            int dataByteIndex = 0;
            for (int i = 0; i < metaDataBytes.length; i += META_DATA_BYTE_SIZE) {
                int keySize = ByteBuffer.wrap(Arrays.copyOfRange(metaDataBytes, i, i + META_DATA_BYTE_SIZE)).getInt();
                i += META_DATA_BYTE_SIZE;
                int valueSize = ByteBuffer.wrap(Arrays.copyOfRange(metaDataBytes, i, i + META_DATA_BYTE_SIZE)).getInt();

                String key = new String(Arrays.copyOfRange(fileDataBytes, dataByteIndex, keySize + dataByteIndex));
                dataByteIndex += keySize;
                String value = new String(Arrays.copyOfRange(fileDataBytes, dataByteIndex, valueSize + dataByteIndex));
                dataByteIndex += valueSize;

                load.put(key, value);
            }


        } catch (FileNotFoundException e) {
            return load;
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 입출력 중 오류 발생");
        }
        return load;
    }

    public void writeFile(Map<String, String> data) {
        new File(fileName + ".bin").delete();
        new File(fileName + ".meta").delete();

        try (BufferedOutputStream metaData = new BufferedOutputStream(new FileOutputStream(fileName + ".meta"));
             BufferedOutputStream fileData = new BufferedOutputStream(new FileOutputStream(fileName + ".bin"))
        ) {

            for (Entry<String, String> entry : data.entrySet()) {
                byte[] keyBytes = entry.getKey().getBytes();
                fileData.write(keyBytes);
                metaData.write(ByteBuffer.allocate(META_DATA_BYTE_SIZE).putInt(keyBytes.length).array());

                byte[] valueBytes = entry.getValue().getBytes();
                fileData.write(valueBytes);
                metaData.write(ByteBuffer.allocate(META_DATA_BYTE_SIZE).putInt(valueBytes.length).array());
            }
        } catch (IOException e) {

        }
    }
}