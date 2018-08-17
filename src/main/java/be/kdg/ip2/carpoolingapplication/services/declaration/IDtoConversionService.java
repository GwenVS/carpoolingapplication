package be.kdg.ip2.carpoolingapplication.services.declaration;

import be.kdg.ip2.carpoolingapplication.domain.user.User;
import be.kdg.ip2.carpoolingapplication.dto.RequestUserDto;
import be.kdg.ip2.carpoolingapplication.dto.UpdateUserDto;
import be.kdg.ip2.carpoolingapplication.dto.UserDto;

public interface IDtoConversionService {
    UserDto convertUserToUserDto(User user);
    User convertUserDtoToUser(UserDto userDto);

    RequestUserDto convertUserToRequestUserDto(User user);

    User convertRequestUserDtoToUser(RequestUserDto requestUserDto);

    UpdateUserDto convertUserToUpdateUserDto(User user);

    User convertUpdateUserDtoToUser(UpdateUserDto updateUserDto);
}
