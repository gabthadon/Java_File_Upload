import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        try {
            // Specify the directory where you want to save the uploaded file
            String uploadDir = "/path/to/upload/directory/";

            // Create the directory if it doesn't exist
            Path directoryPath = Paths.get(uploadDir);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Get the original filename of the uploaded file
            String fileName = file.getOriginalFilename();

            // Specify the path where you want to save the uploaded file
            Path filePath = Paths.get(uploadDir + fileName);

            // Copy the uploaded file to the specified location
            Files.copy(file.getInputStream(), filePath);

            return "File uploaded successfully!";
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }
}
