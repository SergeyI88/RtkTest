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
@WebServlet("/tagupdate")
public class TagUpdate extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { ;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        ObjectMapper objectMapper = new ObjectMapper();
        String list = (String) getServletContext().getAttribute("list");
        CustomArrayList<Tag> customArrayList = objectMapper.readValue(list, CustomArrayList.class);

        for (int i = 0; i < customArrayList.size(); i++) {
            if (customArrayList.get(i).getId().equals(id)) {
                customArrayList.get(i).setName(name);
                req.setAttribute("message", "update");
                synchronized (list) {
                    customArrayList.set(i, new Tag(id, name));
                    getServletContext().setAttribute("list", objectMapper.writeValueAsString(customArrayList));
                }
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            }
        }
        req.setAttribute("message", "объекта с таким id нет");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
