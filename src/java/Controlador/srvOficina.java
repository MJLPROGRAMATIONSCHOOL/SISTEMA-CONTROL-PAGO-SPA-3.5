package Controlador;

import DAO.OficinaDAO;
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
@WebServlet(name = "srvOficina", urlPatterns = {"/oficina"})
public class srvOficina extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "listarOficina":
                    this.listarOficinas(response);
                    break;
                case "agregarOficina":
                    this.registrarOficina(request, response, accion);
                    break;
                case "leerOficina":
                    this.presentarOficina(request, response);
                    break;
                case "editarOficina":
                    this.registrarOficina(request, response, accion);
                    break;
                case "eliminarOficina":
                    this.eliminarOficina(request, response);
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

    private void listarOficinas(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            OficinaDAO ofdao = new OficinaDAO();
            List<Oficina> oficinas = ofdao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(oficinas);
            out.print(json);
        } catch (Exception e) {
            this.printMessage(e.getMessage(), false, response);
        }
    }

    private void registrarOficina(HttpServletRequest request, HttpServletResponse response, String tipo) throws IOException {
        if (request.getParameter("of") != null) {
            Gson gson = new Gson();
            Oficina of = gson.fromJson(request.getParameter("of"), Oficina.class);
            String rpt;
            try {
                OficinaDAO ofic = new OficinaDAO();
                if (tipo.equals("agregarOficina")) {
                    ofic.registrar(of);
                    rpt = "registrados";
                } else {
                    ofic.actualizar(of);
                    rpt = "actualizados";
                }
                response.getWriter().print("{\"msj\": \"Los datos han sido " + rpt + " Correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            } finally {

            }
        }
    }

    private void presentarOficina(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OficinaDAO ofdao;
        Oficina ofc;
        if (request.getParameter("id") != null) {
            ofc = new Oficina();
            ofc.setIdOficina(Integer.parseInt(request.getParameter("id")));
            try {
                ofdao = new OficinaDAO();
                ofc = ofdao.leer(ofc);
                String json = new Gson().toJson(ofc);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        }
    }

    private void eliminarOficina(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Oficina ofc;
        if (request.getParameter("id") != null) {
            ofc = new Oficina();
            ofc.setIdOficina(Integer.parseInt(request.getParameter("id")));
            try {
                OficinaDAO ofdao = new OficinaDAO();
                ofdao.eliminar(ofc);
                this.printMessage("la Surcusal ha sido eliminada correctamente", true, response);
                //response.getWriter().print("{\"msj\": \"la Surcusal ha sido eliminada correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        } else {
            this.printMessage("No se tiene parametro de la Surcusal", false, response);
        }
    }

    private void printMessage(String msjError, boolean rpt, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"rpt\": " + rpt + ", \"msj\": \"" + msjError + "\"}");
    }

}
