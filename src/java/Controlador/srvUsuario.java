package Controlador;

import DAO.ColaboradorDAO;
import DAO.UsuarioDAO;
import Modelo.Colaborador;
import Modelo.Login;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lainc
 */
@WebServlet(name = "srvUsuario", urlPatterns = {"/session"})
public class srvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String accion = request.getParameter("accion");

        switch (accion) {
            case "identificar":
                this.iniciarSession(request, response);
                break;
            case "cerrar":
                cerrarsession(request, response);
                break;
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

    private void iniciarSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("datos") != null) {
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(request.getParameter("datos"), Colaborador.class);

            ColaboradorDAO dao = new ColaboradorDAO();
            try {
                colaborador = dao.identificar(colaborador);
                if (colaborador == null) {
                    this.printMessage("Usuario y/o contraseña incorrectas", false, response);
                } else {
                    if (!colaborador.isEstado()) {
                        this.printMessage("Credenciales no válidas", false, response);
                    } else {
                        request.getSession().setAttribute("usuario", colaborador);
                        this.printMessage("Acceso permitido", true, response);
                    }
                }
            } catch (Exception e) {
                this.printMessage(e.getMessage(), false, response);
            }
        }
    }

    private void printMessage(String msj, boolean rpt, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"rpt\": " + rpt + ", \"msj\": \"" + msj + "\"}");
    }

    private void cerrarsession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("identificar.jsp");
    }

}
