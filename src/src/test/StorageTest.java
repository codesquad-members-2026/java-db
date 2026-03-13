package test;

import main.Storage;
import java.util.NoSuchElementException;
import java.util.List;

public class StorageTest {
    public static void main(String[] args) {
        StorageTest test = new StorageTest();

        System.out.println("=== Storage 단위 테스트 시작 ===");
        test.testSaveAndFind();    // 정상 동작 테스트
        test.testDelete();         // 정상 동작 테스트
        test.testFindException();  // 예외 상황 테스트
        test.testDeleteException();// 예외 상황 테스트
        test.testFindAllKeys();    // 정상 동작 테스트
        System.out.println("=== 모든 테스트 종료 ===");
    }

    // 1. 정상 동작: 저장하고 찾기
    public void testSaveAndFind() {
        Storage storage = new Storage();
        storage.save("user", "wonjae");
        String result = storage.find("user");

        if (result.equals("wonjae")) {
            System.out.println("[성공] 정상 저장 및 조회");
        }
    }

    // 2. 예외 상황: 없는 키 조회 시 에러 발생 여부
    public void testFindException() {
        Storage storage = new Storage();
        try {
            storage.find("none");
            System.out.println("[실패] 없는 키 조회 시 예외가 발생하지 않음");
        } catch (NoSuchElementException e) {
            System.out.println("[성공] 없는 키 조회 시 예외 발생 확인: " + e.getMessage());
        }
    }

    // 3. 정상 동작: 삭제 확인
    public void testDelete() {
        Storage storage = new Storage();
        storage.save("temp", "value");
        storage.delete("temp");

        try {
            storage.find("temp");
            System.out.println("[실패] 삭제 후에도 데이터가 남아있음");
        } catch (NoSuchElementException e) {
            System.out.println("[성공] 데이터 삭제 확인");
        }
    }

    // 4. 예외 상황: 없는 키 삭제 시 에러 발생 여부
    public void testDeleteException() {
        Storage storage = new Storage();
        try {
            storage.delete("ghost");
            System.out.println("[실패] 없는 키 삭제 시 예외가 발생하지 않음");
        } catch (NoSuchElementException e) {
            System.out.println("[성공] 없는 키 삭제 시 예외 발생 확인: " + e.getMessage());
        }
    }

    // 5. 정상 동작: 모든 키 목록 가져오기
    public void testFindAllKeys() {
        Storage storage = new Storage();
        storage.save("k1", "v1");
        storage.save("k2", "v2");
        List<String> keys = storage.findAllKeys();

        if (keys.size() == 2 && keys.contains("k1") && keys.contains("k2")) {
            System.out.println("[성공] 전체 키 목록 조회 확인");
        }
    }
}