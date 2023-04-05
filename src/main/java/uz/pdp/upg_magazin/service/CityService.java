package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;
import uz.pdp.upg_magazin.dto.CityRequestDTO;
import uz.pdp.upg_magazin.entity.CityEntity;
import uz.pdp.upg_magazin.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService implements BaseService<CityEntity, CityRequestDTO> {
    private final CityRepository cityRepository;

    @Override
    public CityEntity add(CityRequestDTO cityRequestDTO) {
        Optional<CityEntity> byName = cityRepository.findByName(cityRequestDTO.getName());
        if (byName.isPresent()) {
            throw new IllegalArgumentException(String.format("The %s already exist", cityRequestDTO.getName()));
        }
        CityEntity cityEntity = CityEntity.builder()
                .name(cityRequestDTO.getName())
                .build();
        return cityRepository.save(cityEntity);
    }

    public CityEntity getByName(String name) {
        return cityRepository.findByName(name).orElseThrow(() -> new RecordNotFountException("CityEntity not fount"));
    }

    public List<CityEntity> list(){
        return cityRepository.findAll();
    }
    @Override
    public boolean delete(int id) {
        Optional<CityEntity> byId = cityRepository.findById(id);
        if (byId.isPresent()) {
            cityRepository.deleteById(id);
            return true;
        }
        throw new RecordNotFountException("CityEntity not fount");
    }

    @Override
    public boolean update(int id, CityRequestDTO cityRequestDTO) {
        return false;
    }
}
