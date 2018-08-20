package be.kdg.ip2.carpoolingapplication.services.implementation;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.dto.*;
import be.kdg.ip2.carpoolingapplication.services.declaration.IDtoConversionService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.ConversionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DtoConversionService implements IDtoConversionService {

    private ModelMapper modelMapper;

    @Autowired
    public DtoConversionService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto convertUserToUserDto(User user)throws ConversionException {
        try {
            return modelMapper.map(user, UserDto.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert User to UserDto: " + e.getMessage());
        }
    }

    @Override
    public User convertUserDtoToUser(UserDto userDto)throws ConversionException {
        try {
            return modelMapper.map(userDto, User.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert UserDto to User: " + e.getMessage());
        }
    }

    @Override
    public RequestUserDto convertUserToRequestUserDto(User user)throws ConversionException {
        try {
            return modelMapper.map(user, RequestUserDto.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert User to RequestUserDto: " + e.getMessage());
        }
    }

    @Override
    public User convertRequestUserDtoToUser(RequestUserDto requestUserDto) throws ConversionException{
        try {
            return modelMapper.map(requestUserDto, User.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert RequestUserDto to User: " + e.getMessage());
        }
    }

    private LocalDate stringToLocalDate(String localDateString) throws ConversionException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            return LocalDate.parse(localDateString, format);
        } catch (Exception e){
            throw new ConversionException("error while parsing date");
        }
    }

    private LocalDateTime stringToLocalDateTime(String localDateTimeString) throws ConversionException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss");
            try {
                return LocalDateTime.parse(localDateTimeString, format);
            } catch (Exception e){
                throw new ConversionException("error while parsing dateTime");
        }
    }
}
