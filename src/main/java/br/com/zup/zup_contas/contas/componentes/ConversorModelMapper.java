package br.com.zup.zup_contas.contas.componentes;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConversorModelMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
