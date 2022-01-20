package veb_prog.veb_prog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "Manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Manufacturer() {

    }
}
