package me.tapumandal.jewellery.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ControllerHelper<Entity> {

    @Autowired
    private   CommonResponseArray commonResponseArray;

    @Autowired
    private   CommonResponseSingle commonResponseSingle;

    @Autowired
    ApplicationPreferences applicationPreferences;

    @Autowired
    JwtUtil jwt;


    protected CommonResponseSingle response(boolean action, HttpStatus status, String message, Entity data){

        commonResponseSingle.setAction(action);
        commonResponseSingle.setStatus(status);
        commonResponseSingle.setMessage(message);
        commonResponseSingle.setData(data);

        return commonResponseSingle;
    }

//    protected CommonResponseSingle response(boolean action, HttpStatus status, String message, Object data){
//
//        commonResponseSingle.setAction(action);
//        commonResponseSingle.setStatus(status);
//        commonResponseSingle.setMessage(message);
//        commonResponseSingle.setData(data);
//
//        return commonResponseSingle;
//    }

    protected  CommonResponseArray<Entity> response(boolean action, HttpStatus status, String message, List<Entity> data, MyPagenation pagenation){

        commonResponseArray.setAction(action);
        commonResponseArray.setStatus(status);
        commonResponseArray.setMessage(message);
        commonResponseArray.setData(data);
        commonResponseArray.setMyPagenation(pagenation);

        return commonResponseArray;
    }

    protected  CommonResponseArray<Entity> response(boolean action, HttpStatus status, String message, List<Entity> data){

        commonResponseArray.setAction(action);
        commonResponseArray.setStatus(status);
        commonResponseArray.setMessage(message);
        commonResponseArray.setData(data);

        return commonResponseArray;
    }

    protected MyPagenation managePagenation(HttpServletRequest request, int totalElement, Pageable pageable) {

        int totalPage = (int) Math.ceil((float)totalElement/(float)pageable.getPageSize());



        String url = request.getRequestURI();
        url = url.substring(7);
        String nextPage = null;
        String prePage = null;

        int pageNum = pageable.getPageNumber();
        if(pageNum<1){
            pageNum = 1;
        }

        if(pageNum < totalPage) {
            nextPage = url + "?page=" + (pageNum + 1) + "?size=" + pageable.getPageSize();
        }
        if(pageNum > 1) {
            prePage = url + "?page=" + (pageNum - 1) + "?size=" + pageable.getPageSize();
        }

        MyPagenation pagenation = new MyPagenation();

        pagenation.setTotalElement(totalElement);
        pagenation.setCurrentPage(pageNum);
        pagenation.setPageSize(pageable.getPageSize());
        pagenation.setTotalPage(totalPage);
        pagenation.setNextPageUrl(nextPage);
        pagenation.setPreviousPageUrl(prePage);

        return pagenation;
    }

    public void storeUserDetails(HttpServletRequest request){

//        System.out.println(request.getHeader("Authorization"));

        if(request.getHeader("Authorization") == null) {
//            System.out.println("NULL");
        }else if(request.getHeader("Authorization").equals("")) {
//            System.out.println("EMPTY");
        }else{

            String authorizationHeader = request.getHeader("Authorization");
            if(!authorizationHeader.isEmpty() && authorizationHeader.length() >10) {
                String token = authorizationHeader.substring(7);
                String username = getUserNameJWT(token);
                applicationPreferences.saveUserByUsername(username);
            }
        }
    }

    private String getUserNameJWT(String token) {
        return jwt.extractUsername(token);
    }

}
