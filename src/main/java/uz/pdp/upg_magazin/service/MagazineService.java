package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;
import uz.pdp.upg_magazin.dto.MagazineDto;
import uz.pdp.upg_magazin.entity.Magazine;
import uz.pdp.upg_magazin.repository.MagazineRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MagazineService implements BaseService<Magazine, MagazineDto> {
    private final MagazineRepository magazineRepository;


    @Override
    public Magazine add(MagazineDto magazineDto) {
        Optional<Magazine> byPhoneNumber = magazineRepository.findByPhoneNumber(magazineDto.getPhoneNumber());
        if (byPhoneNumber.isEmpty()) {
            Magazine magazine = Magazine.of(magazineDto);
            return magazineRepository.save(magazine);
        }
        throw new IllegalArgumentException(String.format("The %s already exist", magazineDto.getPhoneNumber()));
    }

    public List<Magazine> magazineList() {
        return magazineRepository.findAll();
    }

    @Override
    public boolean delete(int id) {
        Optional<Magazine> byId = magazineRepository.findById(id);
        if (byId.isPresent()) {
            magazineRepository.deleteById(id);
            return true;
        }
        throw new RecordNotFountException("Magazine not fount");
    }

    @Override
    public boolean update(int id, MagazineDto magazineDto) {
        Magazine magazine = magazineRepository.findById(id).orElseThrow(() -> new RecordNotFountException(id+" magazine not fount"));
        magazine.setName(magazineDto.getName());
        magazine.setAddress(magazineDto.getAddress());
        magazine.setPhoneNumber(magazineDto.getPhoneNumber());
        magazine.setWorkingTime(magazineDto.getWorkingTime());
        magazineRepository.save(magazine);
        return true;
    }
}
