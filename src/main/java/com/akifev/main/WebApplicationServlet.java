package com.akifev.main;

import com.akifev.entities.*;
import com.akifev.services.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class WebApplicationServlet extends HttpServlet {
    CategoryService cs = new CategoryService();
    ProductService ps = new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //лишний код=)
        //List<ProductEntity> productEntityList = ps.getAll();
        req.setAttribute("items", ps.getAll());
        req.setCharacterEncoding("utf-8");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        if(req.getParameter("form").equals("create")){
            create(req);
        }else{
            //new HashMap == new HashMap<Object,Object>
            Map<String, String> criterions = new HashMap<>();
            criterions.put("name", req.getParameter("name"));
            criterions.put("category_name", req.getParameter("category_name"));
            criterions.put("price_from", req.getParameter("price_from"));
            criterions.put("price_to", req.getParameter("price_to"));
            req.setAttribute("items", ps.search(criterions));
            req.setAttribute("criterions", criterions);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void create(HttpServletRequest req) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryEntity(new CategoryEntity(req.getParameter("category_name")));
        productEntity.setName(req.getParameter("name"));
        productEntity.setPrice(Integer.parseInt(req.getParameter("price")));
        ps.add(productEntity);
        req.setAttribute("items", ps.getAll());
    }


}
