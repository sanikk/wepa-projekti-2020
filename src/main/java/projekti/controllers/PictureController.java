package projekti.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import projekti.domain.ProfileImage;
import projekti.repos.ProfileImageRepository;

/**
 *
 * @author karpo
 */
@Controller
public class PictureController {

    @Autowired
    private ProfileImageRepository profileImageRepository;

    @GetMapping(path = "/media/image/{id}", produces = {"image/png", "image/jpg", "image/jpeg"})
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        return profileImageRepository.getOne(id).getContent();
    }

    @PostMapping("/media/image")
    public String sendImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().matches("image/png|image/jpg|image/jpeg")) {
            ProfileImage p = new ProfileImage(file.getBytes());
            profileImageRepository.save(p);
        }
        return "redirect:/";
    }

}
