package app.ebel.steadybucks.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "stock")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @Id
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "market", nullable = false, length = 30)
    private String market;

    @Column(name = "section_code")
    private Short section_code;

    @Column(name = "section", length = 30)
    private String section;

    @Column(name = "industry_code", length = 10)
    private String industryCode;

    @Column(name = "industry", length = 30)
    private String industry;

    @Column(name = "isSPAC")
    @ColumnDefault(value = "false")
    private Boolean isSPAC;
}
