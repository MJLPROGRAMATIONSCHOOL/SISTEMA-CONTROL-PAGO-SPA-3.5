package Controlador;

import DAO.CargoDAO;
import Modelo.Cargo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lainc
 */
@WebServlet(name = "srvCargo", urlPatterns = {"/cargo"})
public class srvCargo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "listarCargo":
                    this.listarCargos(response);
                    break;
                case "agregarCargo":
                    this.registrarCargo(request, response, accion);
                    break;
                case "leerCargo":
                    this.presentarCargo(request, response);
                    break;
                case "editarCargo":
                    this.registrarCargo(request, response, accion);
                    break;
                case "eliminarCargo":
                    this.eliminarCargo(request, response);
                    break;
            }
        } else {
            this.printMessage("No se indico la operacion a realizar", false, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listarCargos(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            CargoDAO cadao = new CargoDAO();
            List<Cargo> cargos = cadao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(cargos);
            out.print(json);
        } catch (Exception e) {
            this.printMessage(e.getMessage(), false, response);
        }
    }

    private void registrarCargo(HttpServletRequest request, HttpServletResponse response, String tipo) throws IOException {
        if (request.getParameter("car") != null) {
            Gson gson = new Gson();
            Cargo car = gson.fromJson(request.getParameter("car"), Cargo.class);
            String rpt;
            try {
                CargoDAO carg = new CargoDAO();
                if (tipo.equals("agregarCargo")) {
                    carg.registrar(car);
                    rpt = "registrados";
                } else {
                    carg.actualizar(car);
                    rpt = "actualizados";
                }
                response.getWriter().print("{\"msj\": \"Los datos han sido " + rpt + " Correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            } finally {

            }
        }
    }

    private void presentarCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CargoDAO cadao;
        Cargo car;
        if (request.getParameter("id") != null) {
            car = new Cargo();
            car.setIdCargo(Integer.parseInt(request.getParameter("id")));
            try {
                cadao = new CargoDAO();
                car = cadao.leer(car);
                String json = new Gson().toJson(car);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se tiene el parametro del cargo", false, response);
        }
    }

    private void eliminarCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cargo car;
        if (request.getParameter("id") != null) {
            car = new Cargo();
            car.setIdCargo(Integer.parseInt(request.getParameter("id")));
            try {
                CargoDAO cadao = new CargoDAO();
                cadao.eliminar(car);
                response.getWriter().print("{\"msj\": \"El cargo ha sido eliminada correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se tiene el parametro del cargo", false, response);
        }
    }

    private void printMessage(String msjError, boolean rpt, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"rpt\": " + rpt + ", \"msj\": \"" + msjError + "\"}");
    }

}
