package Controlador;

import DAO.EmpresaDAO;
import DAO.OficinaDAO;
import Modelo.Empresa;
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
@WebServlet(name = "srvEmpresa", urlPatterns = {"/empresa"})
public class srvEmpresa extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "listarEmpresa":
                    this.listarEmpresas(response);
                    break;
                case "agregarEmpresa":
                    this.registrarEmpresa(request, response, accion);
                    break;
                case "leerEmpresa":
                    this.presentarEmpresa(request, response);
                    break;
                case "editarEmpresa":
                    this.registrarEmpresa(request, response, accion);
                    break;
                case "eliminarEmpresa":
                    this.eliminarEmpresa(request, response);
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

    private void listarEmpresas(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            EmpresaDAO empdao = new EmpresaDAO();
            List<Empresa> empresas = empdao.listar();
            Gson gson = new Gson();
            String json = gson.toJson(empresas);
            out.print(json);
        } catch (Exception e) {
            this.printMessage(e.getMessage(), response);
        }
    }

    private void registrarEmpresa(HttpServletRequest request, HttpServletResponse response, String tipo) throws IOException {
        if (request.getParameter("empre") != null) {
            Gson gson = new Gson();
            Empresa empre = gson.fromJson(request.getParameter("empre"), Empresa.class);
            String rpt;
            empre.setOficinas(new Oficina());
            empre.getOficinas().setIdOficina(Integer.parseInt(request.getParameter("IdOficina")));
            try {
                EmpresaDAO em = new EmpresaDAO();
                if (tipo.equals("agregarEmpresa")) {
                    em.registrar(empre);
                    rpt = "registrados";
                } else {
                    em.actualizar(empre);
                    rpt = "actualizados";
                }
                response.getWriter().print("{\"msj\": \"Los datos han sido " + rpt + " Correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), response);
            } finally {

            }
        }
    }

    private void presentarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EmpresaDAO empdao;
        Empresa emp;
        if (request.getParameter("id") != null) {
            emp = new Empresa();
            emp.setRutEmpresa(Integer.parseInt(request.getParameter("id")));
            try {
                empdao = new EmpresaDAO();
                emp = empdao.leer(emp);
                String json = new Gson().toJson(emp);
                response.getWriter().print(json);
            } catch (Exception e) {
                this.printMessage(e.getMessage(), response);
            }
        }
    }

    private void eliminarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Empresa em;
        if (request.getParameter("id") != null) {
            em = new Empresa();
            em.setRutEmpresa(Integer.parseInt(request.getParameter("id")));
            try {
                EmpresaDAO empdao = new EmpresaDAO();
                empdao.eliminar(em);
                response.getWriter().print("{\"msj\": \"La surcusal ha sido eliminada correctamente\"}");
            } catch (Exception e) {
                this.printMessage(e.getMessage(), response);
            }
        }
    }

    private void traerDatosExtras(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        try {
            List<Oficina> oficinas = new OficinaDAO().listar();
            response.getWriter().print("{\"oficinas\": " + gson.toJson(oficinas) + ",");
        } catch (Exception e) {
            this.printMessage(e.getMessage(), response);
        }
    }

    private void printMessage(String msj, HttpServletResponse response) throws IOException {
        response.getWriter().print("{\"msj\": \"" + msj + "\"}");
    }

}
