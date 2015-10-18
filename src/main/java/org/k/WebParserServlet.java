package org.k;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Kateryna on 04.08.2015.
 */
public class WebParserServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterName = req.getParameter("SearchByName").trim().toLowerCase();
        String parameterMin = req.getParameter("MinPrice").trim();
        String parameterMax = req.getParameter("MaxPrice").trim();

        BigDecimal minPrice = BigDecimal.ZERO;
        if (parameterMin != null && !parameterMin.equals("") && !parameterMin.isEmpty()){
            minPrice = BigDecimal.valueOf(Double.parseDouble(parameterMin));
        }

        BigDecimal maxPrice = BigDecimal.ZERO;
        if (parameterMax != null && !parameterMax.equals("") && !parameterMax.isEmpty()){
            maxPrice = BigDecimal.valueOf(Double.parseDouble(parameterMax));
        }

        ArrayList<Product> products = DataBaseService.getListOfLaptopsByParameters(parameterName,minPrice,maxPrice);
        req.setAttribute("priceList",products);
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/searchResultsPage.jsp");
        dispatcher.forward(req, resp);
    }
}
