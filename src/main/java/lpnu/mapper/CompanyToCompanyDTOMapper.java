package lpnu.mapper;

import lpnu.dto.Company;
import lpnu.dto.CompanyDTO;
import org.springframework.stereotype.Component;

@Component
public class CompanyToCompanyDTOMapper {
    public Company toEntity(final CompanyDTO companyDTO) {
        final Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        return company;
    }

    public CompanyDTO toDTO(final Company company) {
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }

}
