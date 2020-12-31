package Conexion;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author lainc
 */
public class Fecha {
    Calendar calendar = Calendar.getInstance();    
    String fecha;

    public Fecha() {
    }

    public String Fecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        fecha = sdf.format(calendar.getTime());
//        fecha = calendar.getTime().toString();
        return fecha;
    }

    public String FechaBD() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fecha = sdf.format(calendar.getTime());
        return fecha;
    }
   
}
