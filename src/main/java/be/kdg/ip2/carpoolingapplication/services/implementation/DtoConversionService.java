package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.dto.CreateRideDto;
import be.kdg.ip2.carpoolingapplication.dto.RequestUserDto;
import be.kdg.ip2.carpoolingapplication.dto.UpdateUserDto;
import be.kdg.ip2.carpoolingapplication.dto.UserDto;
import be.kdg.ip2.carpoolingapplication.services.declaration.IDtoConversionService;
import be.kdg.ip2.carpoolingapplication.services.exceptions.ConversionException;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
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

    @Override
    public Ride createRideDtoToRide(CreateRideDto createRideDto) {
        Ride ride = modelMapper.map(createRideDto, Ride.class);
        //modelmapper has trouble converting the date , so set it manually after conversion
        ride.setDepartureTimeOutwardJourney(stringToLocalDateTime(createRideDto.getDepartureTimeOutwardJourney()));
        ride.setDepartureTimeReturnTrip(stringToLocalDateTime(createRideDto.getDepartureTimeReturnTrip()));
        return ride;
    }

    private LocalDate stringToLocalDate(String localDateString) throws ConversionException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate localDate = LocalDate.parse(localDateString, format);
            return localDate;
        } catch (Exception e){
            throw new ConversionException("error while parsing date");
        }
    }

    private LocalDateTime stringToLocalDateTime(String localDateTimeString) throws ConversionException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss");
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString, format);
                return localDateTime;
            } catch (Exception e){
                throw new ConversionException("error while parsing dateTime");
        }
    };
}
