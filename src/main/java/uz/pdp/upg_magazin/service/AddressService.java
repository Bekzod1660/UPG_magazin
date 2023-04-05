package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;
import uz.pdp.upg_magazin.dto.AddressDto;
import uz.pdp.upg_magazin.entity.Address;
import uz.pdp.upg_magazin.repository.AddressRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements BaseService<Address, AddressDto> {
    private final AddressRepository addressRepository;
    private final CityService cityService;

    @Override
    public Address add(AddressDto addressDto) {
        Optional<Address> byName = addressRepository.findByName(addressDto.getName());
        if (byName.isPresent()) {
            throw new IllegalArgumentException(String.format("The %s already exist", addressDto.getName()));
        }
        Address address = Address.of(addressDto);
        address.setCityEntity(cityService.getByName(addressDto.getCityName()));
        return addressRepository.save(address);
    }


    public List<Address> listAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressByName(String name) {
        return addressRepository.findByName(name).orElseThrow(
                () -> new RecordNotFountException("Address not fount")
        );
    }

    @Override
    public boolean delete(int id) {
//        Optional<Address> byId = addressRepository.findById(id);
//        if (byId.isPresent()){
//            addressRepository.deleteById(id);
//            return true;
//        }
//         throw new RecordNotFountException("Address not fount");
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new RecordNotFountException("Address not fount")
        );
        addressRepository.delete(address);
        return true;
    }

    @Override
    public boolean update(int id, AddressDto addressDto) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NoSuchElementException(id + " not fount "));
        address.setName(address.getName());
        addressRepository.save(address);
        return true;
    }
}
