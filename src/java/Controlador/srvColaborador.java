package Controlador;

import DAO.CargoDAO;
import DAO.ColaboradorDAO;
import DAO.OficinaDAO;
import Modelo.Cargo;
import Modelo.Colaborador;
import Modelo.Oficina;
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
@WebServlet(name = "srvColaborador", urlPatterns = {"/colaborador"})
public class srvColaborador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "listarColaborador":
                    this.listarColaborades(response);
                    break;
                case "agregarColaborador":
                    this.registrarColaborador(request, response, accion);
                    break;
                case "leerColaborador":
                    this.presentarColaborador(request, response);
                    break;
                case "editarColaborador":
                    this.registrarColaborador(request, response, accion);
                    break;
                case "eliminarColaborador":
                    this.eliminarColaborador(request, response);
                    break;
                case "cargarDatosCombo":
                    this.traerDatosExtras(response);
                    break;
            }
        } else {
            this.printMessage("No se indico la operacion a realizar", response);
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

    private void traerDatosExtras(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        try {
            List<Oficina> oficinas = new OficinaDAO().listar();
            List<Cargo> cargos = new CargoDAO().listar();
            response.getWriter().print("{\"oficinas\": " + gson.toJson(oficinas) + ","
                    + "\"cargos\": " + gson.toJson(cargos) + ",");
        } catch (Exception e) {
            this.printMessage(e.getMessage(), response);
        }
    }
    
    private void listarColaborades(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            ColaboradorDAO colabodao = new ColaboradorDAO();
            List<Colaborador> colaboradores = colabodao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(colaboradores);
            out.print(json);
        } catch (Exception e) {
            this.printMessage(e.getMessage(), response);
        }
    }

    private void printMessage(String msj, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"msj\": \"" + msj + "\"}");
    }

    private void registrarColaborador(HttpServletRequest request, HttpServletResponse response, String tipo) throws IOException {
        if (request.getParameter("cola") != null) {
            Gson gson = new Gson();
            Colaborador cola = gson.fromJson(request.getParameter("cola"), Colaborador.class);
            String rpt;
            cola.setOficinas(new Oficina());
            cola.getOficinas().setIdOficina(Integer.parseInt(request.getParameter("IdOficina")));
            cola.setCargos(new Cargo());
            cola.getCargos().setIdCargo(Integer.parseInt(request.getParameter("Codigo")));
            try {
                ColaboradorDAO coladao = new ColaboradorDAO();
                if (tipo.equals("agregarColaborador")) {
                    coladao.registrar(cola);
                    rpt = "registrados";
                } else {
                    coladao.actualizar(cola);
                    rpt = "actualizados";
                }
                response.getWriter().print("{\"msj\": \"Los datos han sido " + rpt + " Correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), response);
            } finally {

            }
        }
    }

    private void presentarColaborador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ColaboradorDAO codaos;
        Colaborador col;
        if (request.getParameter("id") != null) {
            col = new Colaborador();
            col.setRutColaborador(Integer.parseInt(request.getParameter("id")));
            try {
                codaos = new ColaboradorDAO();
                col = codaos.leer(col);
                String json = new Gson().toJson(col);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), response);
            }
        }
    }

    private void eliminarColaborador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Colaborador colabs;
        if (request.getParameter("id") != null) {
            colabs = new Colaborador();
            colabs.setRutColaborador(Integer.parseInt(request.getParameter("id")));
            try {
                ColaboradorDAO coldao = new ColaboradorDAO();
                coldao.eliminar(colabs);
                response.getWriter().print("{\"msj\": \"El cargo ha sido eliminada correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), response);
            }
        }
    }
}
