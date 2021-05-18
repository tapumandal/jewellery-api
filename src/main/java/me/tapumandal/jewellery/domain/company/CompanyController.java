package me.tapumandal.jewellery.domain.company;

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
@RequestMapping("/api/v1/company")
public class CompanyController extends ControllerHelper<Company> {

    @Autowired
    CompanyService companyService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createCompany(@ModelAttribute CompanyDto companyDto, HttpServletRequest request) {

        //System.out.println(new Gson().toJson(request.getAttribute("name")));
        //System.out.println(new Gson().toJson(companyDto));

        storeUserDetails(request);

        Company company = companyService.create(companyDto);

        if (company != null) {
            return response(true, HttpStatus.CREATED, "New company inserted successfully", company);
        } else if (company == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Company) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Company) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Company> getCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Company company = companyService.getById(id);

        if (company != null) {
            return response(true, HttpStatus.FOUND, "Company by id: " + id, company);
        } else if (company == null) {
            return response(false, HttpStatus.NO_CONTENT, "Company not found or deleted", (Company) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Company) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Company> getAll(@ModelAttribute ListFilter listFilter, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Company> companys = companyService.getAll(pageable, listFilter);

//        MyPagenation myPagenation = managePagenation(request, companyService.getPageable(pageable), pageable);

        if (!companys.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All company list", companys);
        } else if (companys.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No company found", new ArrayList<Company>());
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Company>());
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateCompany(@RequestBody CompanyDto companyDto, HttpServletRequest request) {

        storeUserDetails(request);

        Company company = companyService.update(companyDto);

        if (company != null) {
            return response(true, HttpStatus.OK, "New company inserted successfully", company);
        } else if (company == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Company) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Company) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Company> deleteCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (companyService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Company by id " + id + " is deleted", (Company) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Company not found or deleted", (Company) null);
        }
    }

}