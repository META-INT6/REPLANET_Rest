package metaint.replanet.rest.point.service;

import metaint.replanet.rest.point.dto.ExchangeDTO;
import metaint.replanet.rest.point.dto.PointFileDTO;
import metaint.replanet.rest.point.entity.Exchange;
import metaint.replanet.rest.point.entity.PointFile;
import metaint.replanet.rest.point.repository.ExchangeRepository;
import metaint.replanet.rest.point.repository.PointFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    private final PointFileRepository pointFileRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository, PointFileRepository pointFileRepository, ModelMapper modelMapper) {
        this.exchangeRepository = exchangeRepository;
        this.pointFileRepository = pointFileRepository;
        this.modelMapper = modelMapper;
    }

    public void exchangePoint(ExchangeDTO newExchange) {
        exchangeRepository.save(modelMapper.map(newExchange, Exchange.class));
    }

    public void savePoinfFile(PointFileDTO newFile) {
        pointFileRepository.save(modelMapper.map(newFile, PointFile.class));
    }
}
