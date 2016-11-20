package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by yegor on 11/15/16.
 */
@Entity
@Table(name = "computer")
public class ComputerEntity {
    private long id;
    private String name;
    private Timestamp introduced;
    private Timestamp discontinued;
    private CompanyEntity company;

    public ComputerEntity() {
    }

    public ComputerEntity(String name, Timestamp introduced, Timestamp discontinued, CompanyEntity company) {
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "introduced")
    public Timestamp getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Timestamp introduced) {
        this.introduced = introduced;
    }

    @Basic
    @Column(name = "discontinued")
    public Timestamp getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Timestamp discontinued) {
        this.discontinued = discontinued;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerEntity that = (ComputerEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (introduced != null ? !introduced.equals(that.introduced) : that.introduced != null) return false;
        if (discontinued != null ? !discontinued.equals(that.discontinued) : that.discontinued != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduced != null ? introduced.hashCode() : 0);
        result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
