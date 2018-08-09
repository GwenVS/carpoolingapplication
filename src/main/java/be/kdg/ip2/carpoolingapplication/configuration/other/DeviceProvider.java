package be.kdg.ip2.carpoolingapplication.configuration.other;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * This is a wrapper class for retrieving the type of device used to connect to the server (used for statistics)
 * Possible values: Normal, Mobile, Tablet
 */
@Component
public class DeviceProvider {

    public Device getCurrentDevice(HttpServletRequest request) {
        return DeviceUtils.getCurrentDevice(request);
    }
}
