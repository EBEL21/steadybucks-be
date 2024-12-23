package app.ebel.staedybucks.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "market", nullable = false, length = 30)
    private String market;

}
