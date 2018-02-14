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
@WebServlet("/tagname")
public class TagName extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ObjectMapper objectMapper = new ObjectMapper();
        String list = (String) getServletContext().getAttribute("list");
        CustomArrayList<Tag> customArrayList = objectMapper.readValue(list, CustomArrayList.class);
        String tag = null;
        for (int i = 0; i < customArrayList.size(); i++) {
            if (customArrayList.get(i).getId().equals(id)) {
                tag = customArrayList.get(i).getName();
                break;
            }
        }
        req.setAttribute("message", tag);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
