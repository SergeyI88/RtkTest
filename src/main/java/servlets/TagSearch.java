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
@WebServlet("/tagsearch")
public class TagSearch extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxCount = Integer.parseInt(req.getParameter("count"));
        String word = req.getParameter("word");
        if (checkSymbols(word)) {
            req.setAttribute("message", "не достаточное количество символов");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String list = (String) getServletContext().getAttribute("list");
        CustomArrayList<Tag> custom;
        synchronized (list) {
            custom =  objectMapper.readValue(list, CustomArrayList.class);
        }
        CustomArrayList<Tag> customArrayList = new CustomArrayList();
        for (int i = 0; i < custom.size() && maxCount > i; i++) {
           if (search(custom.get(i), req.getParameter("word"))) {
               customArrayList.add(custom.get(i));
           }
        }
        req.setAttribute("tags", customArrayList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private boolean search(Tag tag, String word) {
        System.out.println(tag.getName().matches(String.format("[%s]+", word)));
        return tag.getName().matches(String.format("%s.+", "nam", word));
    }
    private boolean checkSymbols(String word) {
        return word.length() < 3;
    }

}
