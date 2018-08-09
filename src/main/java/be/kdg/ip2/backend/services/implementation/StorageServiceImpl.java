package be.kdg.ip2.backend.service.implementation;

import be.kdg.ip2.backend.service.exception.StorageServiceException;
import be.kdg.ip2.backend.service.declaration.StorageService;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
@Primary
public class StorageServiceImpl implements StorageService {

    private Path rootLocation;

    private String location = ""; //Files will be stored here
    private String relativePathToResources = "/src/main/resources/profilePictures/";


    public StorageServiceImpl() {
        //Dummy file to retrieve path information
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        try{
            this.location = helper + relativePathToResources;
            this.rootLocation = Paths.get(location);
        }catch (Exception e){
            System.out.println("LOCATION: " + this.rootLocation);
            e.getMessage();
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageServiceException("Could not initialize storage");
        }
    }

    @Override
    public void store(MultipartFile file) {
        //Clean version of the filename
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageServiceException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check for breaking out of the current directory
                throw new StorageServiceException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageServiceException("Could not store file");
        }

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        }
        catch (IOException e) {
            //todo
            throw new StorageServiceException("Could not store file");
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            //Load the filename from the storage path
            Path file = load(filename);

            //Try to load the url as a resource
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageServiceException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageServiceException("Could not read file");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

}
