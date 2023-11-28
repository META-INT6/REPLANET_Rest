package metaint.replanet.rest.point.controller;

import metaint.replanet.rest.point.dto.ExchangeDTO;
import metaint.replanet.rest.point.dto.PointFileDTO;
import metaint.replanet.rest.point.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService){
        this.exchangeService = exchangeService;
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<?> exchangePoint (@RequestParam("title") String title, HttpServletRequest request,
                                            @RequestParam(value = "file", required = false)MultipartFile pointFile){

        ExchangeDTO newExchange = new ExchangeDTO();
        newExchange.setExchangeDate(new Date());
        newExchange.setTitle(title);
        newExchange.setMemberCode(1);

        exchangeService.exchangePoint(newExchange);

        if(pointFile != null && !pointFile.isEmpty()) {
            String fileOriginName = pointFile.getOriginalFilename();
            String fileExtension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
            String fileSaveName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;
            String filePath = "C:\\filetest";

            PointFileDTO newFile = new PointFileDTO();
            newFile.setFileOriginName(fileOriginName);
            newFile.setFileSaveName(fileSaveName);
            newFile.setFilePath(filePath);
            newFile.setFileExtension(fileExtension);
            newFile.setApplicationCode(001);

            try{
                File pf = new File(filePath + fileSaveName);
                pointFile.transferTo(pf);
                exchangeService.savePoinfFile(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity
                .created(URI.create("/exchanges"))
                .build();
    }
}
