package me.tapumandal.jewellery.domain.category;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.CommonResponseArray;
import me.tapumandal.jewellery.util.CommonResponseSingle;
import me.tapumandal.jewellery.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/category")
public class CategoryController extends ControllerHelper<Category> {

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createCompany(@ModelAttribute CategoryDto categoryDto, HttpServletRequest request) {

        storeUserDetails(request);

        Category category = categoryService.create(categoryDto);

        if (category != null) {
            return response(true, HttpStatus.CREATED, "New category inserted successfully", category);
        } else if (category == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Category) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Category) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Category> getCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Category category = categoryService.getById(id);

        if (category != null) {
            return response(true, HttpStatus.FOUND, "Company by id: " + id, category);
        } else if (category == null) {
            return response(false, HttpStatus.NO_CONTENT, "Company not found or deleted", (Category) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Category) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Category> getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Category> categorys = categoryService.getAll(pageable, listFilter);
//        MyPagenation myPagenation = managePagenation(request, categoryService.getPageable(pageable), pageable);

        if (!categorys.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All category list", categorys);
        } else if (categorys.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No category found", new ArrayList<Category>());
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Category>());
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateCompany(@RequestBody CategoryDto categoryDto, HttpServletRequest request) {

        storeUserDetails(request);

        Category category = categoryService.update(categoryDto);

        if (category != null) {
            return response(true, HttpStatus.OK, "New category inserted successfully", category);
        } else if (category == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Category) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Category) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Category> deleteCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (categoryService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Company by id " + id + " is deleted", (Category) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Company not found or deleted", (Category) null);
        }
    }

}