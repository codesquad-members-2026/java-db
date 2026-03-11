package com.memdb;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class KeyValStorageTest {

    private KeyValStorage newStorage;

    @BeforeEach
    public void setUp(){
        newStorage = new KeyValStorage();
    }

    @Test
    public void setGetNominalTest(){
        newStorage.set("a","10");
        newStorage.set("ac","100");
        try{
            assertThat(newStorage.get("a")).isEqualTo("10");
            assertThat(newStorage.get("ac")).isEqualTo("100");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void setTwiceTest(){
        newStorage.set("a","1");
        newStorage.set("a","100");
        try{
            assertThat(newStorage.get("a")).isEqualTo("100");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getExceptionTest(){
        try{
            assertThatThrownBy(()->newStorage.get("axax")).isInstanceOf(IllegalArgumentException.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteTest(){
        newStorage.set("a","100");
        try{
            newStorage.delete("a");
            assertThatThrownBy(() -> newStorage.get("a")).isInstanceOf(IllegalArgumentException.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void keysTest(){
        newStorage.set("a","1");
        newStorage.set("b","2");
        newStorage.set("c","3");
        newStorage.set("d","4");
        assertThat(newStorage.keys()).isEqualTo("a, b, c, d");
    }
}
