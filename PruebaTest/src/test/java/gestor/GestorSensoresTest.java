package gestor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorSensoresTest {

    GestorSensores sensor;

    @BeforeEach
    public void setupGestorSensor(){
        sensor = new GestorSensores();
    }

    @Test
    public void Borrar_SensorInexistente_ReturnsIllegalArgumenException(){
        //Arrange
        String input = "SensorInexistente";

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {sensor.borrarSensor(input);});
    }

    @Test
    public void Borrar_SensorExistente_ReturnsTrue(){
        //Arrange
        String input ="NuevoSensor";
        sensor.agregarSensor(input);

        //Act
        boolean res = sensor.borrarSensor(input);

        //Assert
        assertTrue(res);
    }

    @Test
    public void Agregar_SensorNuevo(){
        String input="NuevoSensor";

        sensor.agregarSensor(input);
        int numSensores = sensor.getNumeroSensores();

        assertEquals(1,numSensores);
    }

    @Test
    public void Agregar_SensorExistente_ReturnsIllegalArgumentException(){
        String input = "SensorNuevo";
        sensor.agregarSensor(input);

        assertThrows(IllegalArgumentException.class, () -> {sensor.agregarSensor(input);});
    }



}
