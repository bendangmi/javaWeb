package com.atguigu.myssm.myspringmvc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/20-8:08
 */

// 拦截所有以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{
    private Map<String, Object> beanMap = new HashMap<>();
    public DispatcherServlet(){

        try {
            final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            // 1.创建DocumentBuilderFactory对象
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 2.创建DocumentBuilder对象
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            // 4.获取所有bean节点
            final NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0; i < beanNodeList.getLength(); i++){
                final Node beanBode = beanNodeList.item(i);
                if(beanBode.getNodeType() == Node.ELEMENT_NODE){
                    final Element beanElement = (Element) beanBode;
                    final String beanId = beanElement.getAttribute("id");
                    final String className = beanElement.getAttribute("class");
                    final Object beanObj = Class.forName(className).newInstance();
                    final Object controllerBeanObj = beanMap.put(beanId, beanObj);
                    // 视频 9：50
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        // 假设url是：http://localhost:8080/day06/hello.do
        // 那么servletPath是：/hello.do  -> hello  -> HelloController
        // 第一步：/hello.do -> hello
        // 第二步：hello -> HelloController

        final String servletPath = request.getServletPath();
        final int index = servletPath.lastIndexOf(".do");
        final String substring = servletPath.substring(1, index);

        System.out.println(substring);

    }
}
