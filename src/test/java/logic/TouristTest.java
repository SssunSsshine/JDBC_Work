package logic;

import data.TestData;
import entity.Tourist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.ConnectionFactory;
import repository.TouristRepository;

import java.sql.SQLException;

import static data.TestData.*;

public class TouristTest {

    private final TouristRepository touristRepository = new TouristRepository(new ConnectionFactory());

    @Test
    public void getDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> touristRepository.get(ID_PETROVA));
    }

    @Test
    public void insertDoesNotThrowExceptionWithNullPatronymicNull() throws SQLException {
        if (touristRepository.get(ALEX_TOURIST.getPhone()) != null){
            touristRepository.deleteByPhone(TestData.ALEX_TOURIST.getPhone());
        }

        Assertions.assertDoesNotThrow(() -> touristRepository.insert(TestData.ALEX_TOURIST));
    }

    @Test
    public void insertDoesNotThrowExceptionWithNullEmail() throws SQLException {
        if (touristRepository.get(JAMES_TOURIST.getPhone()) != null){
            touristRepository.deleteByPhone(TestData.JAMES_TOURIST.getPhone());
        }

        Assertions.assertDoesNotThrow(() -> touristRepository.insert(TestData.JAMES_TOURIST));
    }

    @Test
    public void touristIsInserted() throws SQLException {
        if (touristRepository.get(ALEX_TOURIST.getPhone()) != null){
            touristRepository.deleteByPhone(TestData.ALEX_TOURIST.getPhone());
        }

        touristRepository.insert(TestData.ALEX_TOURIST);
        Tourist touristAfter = touristRepository.get(ALEX_TOURIST.getPhone());

        touristRepository.deleteByPhone(TestData.ALEX_TOURIST.getPhone());

        Assertions.assertNotEquals(null, touristAfter);
    }

    @Test
    public void deleteDoesNotThrowException() throws SQLException {
        if (touristRepository.get(ALEX_TOURIST.getPhone()) == null){
            touristRepository.insert(ALEX_TOURIST);
        }

        Assertions.assertDoesNotThrow(() -> touristRepository.deleteByPhone(ALEX_TOURIST.getPhone()));
    }

    @Test
    public void touristIsDeleted() throws SQLException {
        Tourist tourist;
        if (touristRepository.get(ALEX_TOURIST.getPhone()) == null){
            touristRepository.insert(ALEX_TOURIST);
        }

        try{
            touristRepository.deleteByPhone(ALEX_TOURIST.getPhone());
        }catch (NullPointerException e){
            System.out.println(e);
            return;
        }

        tourist = touristRepository.get(ALEX_TOURIST.getPhone());
        touristRepository.insert(ALEX_TOURIST);

        Assertions.assertNull(tourist);
    }

    @Test
    public void updateDoesNotThrowException() throws SQLException {
        Tourist mamedov = touristRepository.get(ID_MAMEDOV);

        Assertions.assertDoesNotThrow(()->touristRepository.updateNameByID(mamedov, "DAWG"));
    }

    @Test
    public void touristIsUpdated() throws SQLException {
        touristRepository.updateNameByID(touristRepository.get(ID_MAMEDOV), "????????");
        String nameBefore = touristRepository.get(ID_MAMEDOV).getName();

        touristRepository.updateNameByID(touristRepository.get(ID_MAMEDOV), "SHEEESH");
        String nameAfter = touristRepository.get(ID_MAMEDOV).getName();

        Assertions.assertNotEquals(nameBefore, nameAfter);
    }
}
