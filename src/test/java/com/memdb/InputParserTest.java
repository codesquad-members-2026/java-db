package com.memdb;


import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;


public class InputParserTest {


    InputParser parser;
    KeyValStorage storage;


    @BeforeEach
    public void setUp(){
        storage = new KeyValStorage();
        parser = new InputParser(storage);
        parser.runCommand("SET a aaaa");
        parser.runCommand("SET b bbbb");
        parser.runCommand("SET c cccc");
        parser.runCommand("SET d dddd");
    }




    @Test
    public void parserSetTest(){
        parser.runCommand("SET jon hello");
        parser.runCommand("SET hana hi");
        parser.runCommand("SET gabi helo");
        parser.runCommand("SET Wanja ello");
        try{
            assertThat(storage.get("jon")).isEqualTo("hello");
            assertThat(storage.get("hana")).isEqualTo("hi");
            assertThat(storage.get("gabi")).isEqualTo("helo");
            assertThat(storage.get("Wanja")).isEqualTo("ello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void parserDeleteTest(){
        try{


            storage.delete("a");
            storage.delete("b");
            storage.delete("c");
            storage.delete("d");
            assertThatThrownBy(() -> storage.get("a")).isInstanceOf(Exception.class);
            assertThatThrownBy(() ->storage.get("b")).isInstanceOf(Exception.class);
            assertThatThrownBy(() ->storage.get("c")).isInstanceOf(Exception.class);
            assertThatThrownBy(() ->storage.get("d")).isInstanceOf(Exception.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }

}
