package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;
import uz.pdp.upg_magazin.dto.CityDto;
import uz.pdp.upg_magazin.entity.City;
import uz.pdp.upg_magazin.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService implements BaseService<City, CityDto> {
    private final CityRepository cityRepository;

    @Override
    public City add(CityDto cityDto) {
        Optional<City> byName = cityRepository.findByName(cityDto.getName());
        if (byName.isPresent()) {
            throw new IllegalArgumentException(String.format("The %s already exist", cityDto.getName()));
        }
        City city = City.builder()
                .name(cityDto.getName()).build();
        return cityRepository.save(city);
    }

    public City getByName(String name) {
        return cityRepository.findByName(name).orElseThrow(() -> new RecordNotFountException("City not fount"));
    }

    public List<City> list(){
        return cityRepository.findAll();
    }
    @Override
    public boolean delete(int id) {
        Optional<City> byId = cityRepository.findById(id);
        if (byId.isPresent()) {
            cityRepository.deleteById(id);
            return true;
        }
        throw new RecordNotFountException("City not fount");
    }

    @Override
    public boolean update(int id, CityDto cityDto) {
        return false;
    }
}
