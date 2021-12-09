package lpnu.resource;

import lpnu.dto.CompanyDTO;
import lpnu.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<CompanyDTO> getAllCompanies() {

        return companyService.getAllCompanies();
    }

    @GetMapping("/companies/{id}")
    public CompanyDTO getCompanyById(@PathVariable final Long id) {

        return companyService.getCompanyById(id);
    }

    @PostMapping("/companies")
    public CompanyDTO saveCompany(@RequestBody @Validated final CompanyDTO companyDTO) {
        return companyService.saveCompany(companyDTO);
    }

    @PutMapping("/companies")
    public CompanyDTO updateCompany(@RequestBody @Validated final CompanyDTO companyDTO) {
        return companyService.updateCompany(companyDTO);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity deleteCompanyById(@PathVariable final Long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.ok().build();
    }
}
