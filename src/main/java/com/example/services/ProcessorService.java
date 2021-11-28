package com.example.services;

import com.example.model.Cnab;
import com.example.model.ETipo;
import com.example.model.LojaDTO;
import com.example.repositories.ProcessorRepository;
import com.example.utils.BussinessException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProcessorService {

    private static final Logger LOG = Logger.getLogger(ProcessorService.class.getName());

    @Autowired
    private ProcessorRepository repository;

    public boolean processFile(MultipartFile file) throws IOException, BussinessException {
        String fileStr = new String(file.getBytes(), Charset.forName("UTF-8"));
        boolean success = true;
        int index = 0;
        List<String> movimentos = (List<String>) CollectionUtils.arrayToList(fileStr.split("\n"));
        for (String mov : movimentos) {
            Cnab cnab = new Cnab();
            try {
                int idTipo = Integer.valueOf(mov.substring(0, 1).trim());
                int cpf = Integer.valueOf(mov.substring(9, 19).trim());
                Double valor = Double.valueOf(mov.substring(9, 19).trim()) / 100;
                LocalTime hora = LocalTime.parse(mov.substring(42, 48).trim(), DateTimeFormatter.ofPattern("HHmmss"));
                LocalDate data = LocalDate.parse(mov.substring(1, 9).trim(), DateTimeFormatter.ofPattern("yyyyMMdd"));
                cnab.setTipo(ETipo.getETipoById(idTipo));
                cnab.setData(data);
                cnab.setValor(valor);
                cnab.setCpf(cpf);
                cnab.setCartao(mov.substring(30, 42).trim());
                cnab.setHora(hora);
                cnab.setDonoLoja(mov.substring(48, 62).trim());
                cnab.setNomeLoja(mov.substring(62, 80).trim());
                repository.save(cnab);
                index++;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                success = false;
                LOG.warning(String.format("ERROR: cpf-%d index-%d message-%s", cnab.getCpf(), index, e.getLocalizedMessage()));
            }
        }
        System.out.println("Cantidade de movimentos: " + index);
        return success;
    }

    public List<LojaDTO> listMovimentos() {
        Map<String, LojaDTO> map = new HashMap<>();
        List<LojaDTO> lojas = new ArrayList<>();
        List<Cnab> cnabs = repository.findAll();
        cnabs.forEach(cnab -> {
            if (!map.isEmpty() && map.keySet().contains(cnab.getNomeLoja())) {
                map.get(cnab.getNomeLoja()).setTotal(map.get(cnab.getNomeLoja()).getTotal() + cnab.getValor());
                map.get(cnab.getNomeLoja()).getOperaciones().add(cnab);
            } else {
                List<Cnab> operaciones = new ArrayList<>();
                operaciones.add(cnab);
                map.putIfAbsent(cnab.getNomeLoja(), new LojaDTO(cnab.getNomeLoja(), cnab.getValor(), operaciones));
            }
        });
        return map.values().parallelStream().collect(Collectors.toList());
    }
    
    public void limparTable(){
        repository.deleteAll();
    }
}
