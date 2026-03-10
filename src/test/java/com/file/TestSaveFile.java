package com.file;

import com.db.KeyValStorage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.*;

public class TestSaveFile {

    KeyValStorage resultStorage;
    SaveFile saveSystem;


    @BeforeEach
    public void setUp(){
        saveSystem = new SaveFile();
    }


    @ParameterizedTest
    @CsvSource({
            "a , 1000",
            "b, 200012",
            "jon, asdasda",
            "caaa, asda"
    })
    public void testSerialization(String k, String v){

        byte[] serialized = saveSystem.serializeKVPair(k,v);

        ByteBuffer bufferedBytes = ByteBuffer.wrap(serialized);

        int kLength = bufferedBytes.getInt();
        byte[] keyBytes = new byte[kLength];
        bufferedBytes.get(keyBytes);
        int vLength = bufferedBytes.getInt();
        byte[] valBytes = new byte[vLength];
        bufferedBytes.get(valBytes);

        assertThat(keyBytes).isEqualTo(k.getBytes());
        assertThat(kLength).isEqualTo(k.getBytes().length);
        assertThat(valBytes).isEqualTo(v.getBytes());
        assertThat(vLength).isEqualTo(v.getBytes().length);
    }

//    @Test
//    public void testLoad(@TempDir Path testDir){
//        saveSystem.setFileName(testDir);
//
//    }
//
//    @Test
//    public void testSave(@TempDir String dir){
//
//    }



}
