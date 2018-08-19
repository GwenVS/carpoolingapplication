package be.kdg.ip2.carpoolingapplication.services.implementation;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.dto.*;
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

    @Override
    public UpdateUserDto convertUserToUpdateUserDto(User user)throws ConversionException {
        try {
            return modelMapper.map(user, UpdateUserDto.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert User ot UpdateUserDto: " + e.getMessage());
        }
    }

    @Override
    public User convertUpdateUserDtoToUser(UpdateUserDto updateUserDto) {
        try {
            return modelMapper.map(updateUserDto, User.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert UpdateUserDto to User: " + e.getMessage());
        }
    }

    @Override
    public Ride createRideDtoToRide(CreateRideDto createRideDto) throws ConversionException{
        try {
            Ride ride = modelMapper.map(createRideDto, Ride.class);
            //modelmapper has trouble converting the date because it enters as a string,
            // so set it manually after conversion to LocalDateTime
            ride.setDepartureTimeOutwardJourney(stringToLocalDateTime(createRideDto.getDepartureTimeOutwardJourney()));
            ride.setDepartureTimeReturnTrip(stringToLocalDateTime(createRideDto.getDepartureTimeReturnTrip()));
            return ride;
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert CreateRideDto to Ride: " + e.getMessage());
        }
    }

    @Override
    public CreateRideDto rideToCreateRideDto(Ride ride) throws ConversionException{
        try {
            return modelMapper.map(ride, CreateRideDto.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert ride to CreateRideDto: " + e.getMessage());
        }
    }

    @Override
    public GetRideDto rideToGetRideDto(Ride ride) {
        try {
            GetRideDto getRideDto = modelMapper.map(ride, GetRideDto.class);
            System.out.println(getRideDto);
            return getRideDto;
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert ride to CreateRideDto: " + e.getMessage());
        }
    }

    @Override
    public CarDto carToCarDto(Car car) {
        try {
            return modelMapper.map(car, CarDto.class);
        } catch (Exception e) {
            throw new ConversionException("@DtoConversionService: unable to convert Car to CarDto: " + e.getMessage());
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
