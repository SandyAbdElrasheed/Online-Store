/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinestore.servlets;

import com.onlinestore.daos.UserDAO;
import com.onlinestore.models.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class SignupServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        UserDAO userDAO = new UserDAO();
        //recievedata
        if (userDAO.isExisting(request.getParameter("name"))) {
            response.sendRedirect("./customer/RegisterError.jsp");
        } else {
            user.setUserName(request.getParameter("name"));

            user.setPassword(request.getParameter("pass"));
            user.setEmail(request.getParameter("email"));
            user.setBirthday(request.getParameter("birthday"));
            user.setJob(request.getParameter("job"));
            user.setAddress(request.getParameter("address"));
            user.setInterests(request.getParameter("interests"));
            user.setCreditLimit(1000);
//            user.setCreditLimit(Integer.parseInt(request.getParameter("credit_limit")));
            user.setIsAdmin(false);

            boolean success = userDAO.save(user);
            if (success) {
                response.sendRedirect("./customer/Home.jsp");
            } else {
                response.getWriter().println("regestration failed!");
            }

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

}
