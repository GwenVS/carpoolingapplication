package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.dto.RequestUserDto;
import be.kdg.ip2.carpoolingapplication.dto.UpdateUserDto;
import be.kdg.ip2.carpoolingapplication.dto.UserDto;
import be.kdg.ip2.carpoolingapplication.services.declaration.IDtoConversionService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DtoConversionService implements IDtoConversionService {

    private ModelMapper modelMapper;

    @Autowired
    public DtoConversionService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User convertUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public RequestUserDto convertUserToRequestUserDto(User user) {
        RequestUserDto requestUserDto = modelMapper.map(user, RequestUserDto.class);
        return requestUserDto;
    }

    @Override
    public User convertRequestUserDtoToUser(RequestUserDto requestUserDto) {
        return modelMapper.map(requestUserDto, User.class);
    }

    @Override
    public UpdateUserDto convertUserToUpdateUserDto(User user) {
        return modelMapper.map(user, UpdateUserDto.class);
    }

    @Override
    public User convertUpdateUserDtoToUser(UpdateUserDto updateUserDto) {
        return modelMapper.map(updateUserDto, User.class);
    }

    private Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
        @Override
        protected LocalDate convert(String source) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(source, format);
            return localDate;
        }
    };
}
