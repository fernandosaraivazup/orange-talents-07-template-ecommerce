package br.com.zupacademy.fpsaraiva.mercadolivre.uploadimagens;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UploaderImagens {

    public List<String> upload(List<MultipartFile> imagens){
        return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
                .collect(Collectors.toList());
    }

}