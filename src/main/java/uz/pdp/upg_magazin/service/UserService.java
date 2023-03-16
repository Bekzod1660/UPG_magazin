package uz.pdp.upg_magazin.service;

import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.dto.ApiResponse;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.entity.User;

import java.util.List;

@Service
public class UserService implements  BaseService<UserRequestDto, ApiResponse>{
    @Override
    public ApiResponse add(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public ApiResponse delete(int id) {
        return null;
    }

    @Override
    public List<UserRequestDto> listObject() {
        return null;
    }

    @Override
    public ApiResponse update(int id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserRequestDto getById(int id) {
        return null;
    }
}
