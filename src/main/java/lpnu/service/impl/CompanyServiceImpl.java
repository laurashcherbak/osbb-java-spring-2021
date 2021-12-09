package lpnu.service.impl;

import lpnu.dto.Company;
import lpnu.dto.CompanyDTO;
import lpnu.mapper.CompanyToCompanyDTOMapper;
import lpnu.repository.CompanyRepository;
import lpnu.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyToCompanyDTOMapper companyMapper;


    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.getAllCompanies().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(final Long id) {
        return companyMapper.toDTO(companyRepository.getCompanyById(id));
    }

    @Override
    public CompanyDTO saveCompany(final CompanyDTO companyDTO) {
        final Company company = companyMapper.toEntity(companyDTO);
        companyRepository.saveCompany(company);
        return companyMapper.toDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(final CompanyDTO companyDTO) {
        final Company company = companyMapper.toEntity(companyDTO);
        return companyMapper.toDTO(companyRepository.updateCompany(company));
    }

    @Override
    public void deleteCompanyById(final Long id) {
        companyRepository.deleteCompanyById(id);

    }
}
