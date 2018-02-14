package servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CustomArrayList;
import entity.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/tags")
public class TagsServlet extends HttpServlet {





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxCount = Integer.parseInt(req.getParameter("count"));
        ObjectMapper objectMapper = new ObjectMapper();
        String list = (String) getServletContext().getAttribute("list");
        System.out.println(list);
        CustomArrayList<Tag> custom;
        synchronized (list) {
            custom = objectMapper.readValue(list, CustomArrayList.class);
        }
        CustomArrayList<Tag> customArrayList = new CustomArrayList();
        for (int i = 0; i < custom.size() && maxCount > i; i++) {
            customArrayList.add(custom.get(i));
        }
        req.setAttribute("tags", customArrayList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
