package me.tapumandal.jewellery.domain.company;

import me.tapumandal.jewellery.repository.Repository;

public interface CompanyRepository extends Repository<Company> {

    public Company getCompanyFirstTime(int companyId);
}
