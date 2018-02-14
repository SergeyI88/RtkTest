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
@WebServlet("/tagadd")
public class TagAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        ObjectMapper objectMapper = new ObjectMapper();
        String list = (String) getServletContext().getAttribute("list");
        CustomArrayList<Tag> customArrayList;
        synchronized (list) {
            customArrayList = objectMapper.readValue(list, CustomArrayList.class);
        }
        if(checkOnUnique(id, customArrayList)) {
            req.setAttribute("message", "не уникальное id");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else if(checkSymbols(name)) {
            req.setAttribute("message", "не достаточное количество символов");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Объект добавлен");
            customArrayList.add(new Tag(id, name));
            synchronized (list) {
                getServletContext().setAttribute("list", objectMapper.writeValueAsString(customArrayList));
            }
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }



    private boolean checkOnUnique(String id, CustomArrayList<Tag> customArrayList) {
        System.out.println(customArrayList.stream().filter(t -> t.getId().equals(id)).count());
       return 0 != customArrayList.stream().filter(t -> t.getId().equals(id)).count();
    }
    private boolean checkSymbols(String name) {
        return name.length() < 3;
    }
}
