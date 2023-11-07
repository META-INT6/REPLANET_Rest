package metaint.replanet.rest.point.controller;

import metaint.replanet.rest.point.dto.ExchangeDTO;
import metaint.replanet.rest.point.dto.PointFileDTO;
import metaint.replanet.rest.point.entity.Exchange;
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
    public ResponseEntity<?> exchangePoint (@RequestPart(value="title") String title,
                                            @RequestPart(value = "memberCode") String memberCodeStr,
                                            @RequestPart(value="file")MultipartFile pointFile){

        int memberCode = Integer.parseInt(memberCodeStr);

        System.out.println("제목 넘어왔니?" + title);
        System.out.println("코드 넘어왔니?" + memberCode);

        if(title != null && pointFile != null) {
            ExchangeDTO newExchange = new ExchangeDTO();
            newExchange.setExchangeDate(new Date());
            newExchange.setTitle(title);
            newExchange.setMemberCode(memberCode);

            System.out.println(newExchange);

            int savedExchangeCode = exchangeService.exchangePoint(newExchange);

            System.out.println(savedExchangeCode);


            String fileOriginName = pointFile.getOriginalFilename();
            String fileExtension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
            String fileSaveName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;
            String filePath = "C:\\filetest";

            PointFileDTO newFile = new PointFileDTO();
            newFile.setFileOriginName(fileOriginName);
            newFile.setFileSaveName(fileSaveName);
            newFile.setFilePath(filePath);
            newFile.setFileExtension(fileExtension);
            newFile.setApplicationCode(savedExchangeCode);

            try{
                File pf = new File(filePath + "/" + fileSaveName);
                pointFile.transferTo(pf);
                exchangeService.savePointFile(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return ResponseEntity
                .created(URI.create("/exchanges"))
                .build();
    }
}