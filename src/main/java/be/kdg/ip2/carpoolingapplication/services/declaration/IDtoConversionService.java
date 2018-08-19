package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.Ride;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.dto.*;

public interface IDtoConversionService {
    UserDto convertUserToUserDto(User user);
    User convertUserDtoToUser(UserDto userDto);

    RequestUserDto convertUserToRequestUserDto(User user);

    User convertRequestUserDtoToUser(RequestUserDto requestUserDto);

    UpdateUserDto convertUserToUpdateUserDto(User user);

    User convertUpdateUserDtoToUser(UpdateUserDto updateUserDto);

    Ride createRideDtoToRide(CreateRideDto createRideDto);

    CreateRideDto rideToCreateRideDto(Ride ride);

    CarDto carToCarDto(Car car);

    GetRideDto rideToGetRideDto(Ride ride);
}
