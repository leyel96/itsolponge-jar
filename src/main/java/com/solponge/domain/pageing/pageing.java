package com.solponge.domain.pageing;

import com.solponge.domain.jobinfo.JopInfoService;
import com.solponge.domain.jobinfo.JopInfoVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class pageing {
    int pageSize;
    int currentPage;
    int start;
    int end;
    int totalPages;
    public pageing(int pageSize, HttpServletRequest request, List<?> data, Model model, String AttriButeName){
        this.pageSize=pageSize;
        this.currentPage=(request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        this.start = (currentPage - 1) * pageSize;
        this.end = Math.min(start + pageSize, data.size());
        this.totalPages = (int) Math.ceil((double) data.size() / pageSize);
        List<?> subVo = data.subList(start, end);
        model.addAttribute(AttriButeName, subVo);
        System.out.println("totalPages_"+totalPages);
        System.out.println("currentPage_"+currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("url", "?");
    }
    public pageing(int pageSize, HttpServletRequest request, List<?> data, Model model, String AttriButeName, String url){
        this.pageSize=pageSize;
        this.currentPage=(request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        this.start = (currentPage - 1) * pageSize;
        this.end = Math.min(start + pageSize, data.size());
        this.totalPages = (int) Math.ceil((double) data.size() / pageSize);
        List<?> subVo = data.subList(start, end);
        model.addAttribute(AttriButeName, subVo);
        System.out.println("currentPage_ "+currentPage);
        System.out.println("totalPages_ "+totalPages);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("status", "Yes");

        url_handler(url, model);

    }

    public pageing(int pageSize, HttpServletRequest request, int data, Model model, String AttriButeName, JopInfoService jopinfoService){
        this.pageSize=pageSize;
        this.currentPage=(request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        this.start = (currentPage - 1) * pageSize;
        this.end = Math.min(start + pageSize, data);
        this.totalPages = (int) Math.ceil((double) data / pageSize);
        List<JopInfoVo> paginatedProducts = jopinfoService.getJopInfoListpage(start, end);
        for(JopInfoVo d: paginatedProducts){
            System.out.println(d.getCompanyname());
        }
        model.addAttribute(AttriButeName, paginatedProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("url", "?");

    }

    public pageing(int pageSize, HttpServletRequest request, int data, Model model, String AttriButeName, JopInfoService jopinfoService, String SearchSelec,String SearchValue, String url){
        this.pageSize=pageSize;
        this.currentPage=(request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        this.start = (currentPage - 1) * pageSize;
        this.end = Math.min(start + pageSize, data);
        this.totalPages = (int) Math.ceil((double) data / pageSize);
        List<JopInfoVo> paginatedProducts = jopinfoService.JopInfosearchlist_page(SearchSelec, SearchValue, start, end);
        model.addAttribute(AttriButeName, paginatedProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("url", "?");

        url_handler(url, model);

    }
    private void url_handler(String url, Model model){
        System.out.println("url은"+url);
        if (url.contains("&page=")){
            int idx = url.indexOf("&page=");
            url = url.substring(0, idx);
        }
        String inputurl ="";
        if (url.contains("SearchSelect")){
            inputurl += "search?"+url+"&";
        } else {
            inputurl += "?";
        }
        System.out.println("들어가는 url: "+inputurl);
        model.addAttribute("url", inputurl);
        model.addAttribute("status", "Yes");
    }
}
