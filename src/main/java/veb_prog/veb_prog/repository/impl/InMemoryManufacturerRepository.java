package veb_prog.veb_prog.repository.impl;

import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.bootstrap.DataHolder;
import veb_prog.veb_prog.model.Manufacturer;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }
    public Optional<Manufacturer> findByID(Long id){
        return DataHolder.manufacturers
                .stream()
                .filter(r->r.getId().equals(id))
                .findFirst();
    }
    public Optional<Manufacturer> save(String name, String address){
        Manufacturer manufacturer = new Manufacturer(name, address);
        DataHolder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }
    public boolean delete(Long id){
        return DataHolder.manufacturers.removeIf(r->r.getId().equals(id));
    }
}
