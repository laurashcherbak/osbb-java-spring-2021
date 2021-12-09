package lpnu.service;

import lpnu.dto.CompanyDTO;
import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getAllCompanies();
    CompanyDTO getCompanyById(final Long id);
    CompanyDTO saveCompany(final CompanyDTO companyDTO);
    CompanyDTO updateCompany(final CompanyDTO companyDTO);
    void deleteCompanyById(final Long id);
}
