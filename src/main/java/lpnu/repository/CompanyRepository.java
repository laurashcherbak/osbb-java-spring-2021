package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.dto.Company;
import lpnu.util.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private List<Company> companies;

    private Long id = 1L;

    @PostConstruct
    public void init() {

        final Path file = Paths.get("companies.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            companies = JacksonUtil.deserialize(savedUsersAsString, new TypeReference<List<Company>>() {});

            if (companies == null) {
                companies = new ArrayList<>();
                return;
            }

            final Long maxId = companies.stream().mapToLong(Company::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e){
            System.out.println("We have an issue");
            companies = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("companies.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(companies), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies); // щоб ліст persons не можна було змінити з service
    }


    public Company getCompanyById(final Long id) {
        return companies.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        //.orElseThrow(() -> new ServiceException(400, "person with id {" + id + "} not found"));
    }

    public Company saveCompany(final Company company) {
        companies.add(company);
        return company;
    }

    public Company updateCompany(final Company company) {
        final Company savedCompany = getCompanyById(company.getId());
        savedCompany.setName(company.getName());
        return savedCompany;
    }

    public void deleteCompanyById(final Long id) {
        companies = companies.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }

}
