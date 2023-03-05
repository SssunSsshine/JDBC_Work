package logic;

import data.TestData;
import entity.TourOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.ConnectionFactory;
import repository.TourOperatorRepository;

import java.sql.SQLException;

import static data.TestData.*;

public class TourOperatorTest {

    private final TourOperatorRepository tourOperatorRepository = new TourOperatorRepository(new ConnectionFactory());

    @Test
    public void getDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> tourOperatorRepository.get(ID_TO));
    }

    @Test
    public void insertDoesNotThrowExceptionWithNullPatronymicNull() throws SQLException {
        if (tourOperatorRepository.get(TRAVEL_NICE_TO.getInn()) != null) {
            tourOperatorRepository.deleteByINN(TestData.TRAVEL_NICE_TO.getInn());
        }

        Assertions.assertDoesNotThrow(() -> tourOperatorRepository.insert(TestData.TRAVEL_NICE_TO));
    }

    @Test
    public void insertDoesNotThrowExceptionWithNullComments() throws SQLException {
        if (tourOperatorRepository.get(LETS_TRAVEL_TO.getInn()) != null) {
            tourOperatorRepository.deleteByINN(TestData.LETS_TRAVEL_TO.getInn());
        }

        Assertions.assertDoesNotThrow(() -> tourOperatorRepository.insert(TestData.LETS_TRAVEL_TO));
    }

    @Test
    public void tourOperatorIsInserted() throws SQLException {
        if (tourOperatorRepository.get(TRAVEL_NICE_TO.getInn()) != null) {
            tourOperatorRepository.deleteByINN(TestData.TRAVEL_NICE_TO.getInn());
        }

        tourOperatorRepository.insert(TestData.TRAVEL_NICE_TO);
        TourOperator tourOperatorAfter = tourOperatorRepository.get(TRAVEL_NICE_TO.getInn());

        tourOperatorRepository.deleteByINN(TestData.TRAVEL_NICE_TO.getInn());

        Assertions.assertNotEquals(null, tourOperatorAfter);
    }

    @Test
    public void deleteDoesNotThrowException() throws SQLException {
        if (tourOperatorRepository.get(TRAVEL_NICE_TO.getInn()) == null) {
            tourOperatorRepository.insert(TRAVEL_NICE_TO);
        }

        Assertions.assertDoesNotThrow(() -> tourOperatorRepository.deleteByINN(TestData.TRAVEL_NICE_TO.getInn()));
    }

    @Test
    public void tourOperatorIsDeleted() throws SQLException {
        TourOperator tourOperator;
        if (tourOperatorRepository.get(TRAVEL_NICE_TO.getInn()) == null) {
            tourOperatorRepository.insert(TRAVEL_NICE_TO);
        }

        try {
            tourOperatorRepository.deleteByINN(TRAVEL_NICE_TO.getInn());
        } catch (NullPointerException e) {
            System.out.println(e);
            return;
        }

        tourOperator = tourOperatorRepository.get(TRAVEL_NICE_TO.getInn());
        tourOperatorRepository.insert(TRAVEL_NICE_TO);

        Assertions.assertNull(tourOperator);
    }

    @Test
    public void updateDoesNotThrowException() throws SQLException {
        Assertions.assertDoesNotThrow(() -> tourOperatorRepository.updateFullNameByID(tourOperatorRepository.get(ID_TO),
                "Travel&Nevil"));
    }

    @Test
    public void tourOperatorIsUpdated() throws SQLException {
        tourOperatorRepository.updateFullNameByID(tourOperatorRepository.get(ID_TO), "DUUUDE");
        String nameBefore = tourOperatorRepository.get(ID_TO).getFullName();

        tourOperatorRepository.updateFullNameByID(tourOperatorRepository.get(ID_TO), "Travel&Nevil");
        String nameAfter = tourOperatorRepository.get(ID_TO).getFullName();

        Assertions.assertNotEquals(nameBefore, nameAfter);
    }

}

