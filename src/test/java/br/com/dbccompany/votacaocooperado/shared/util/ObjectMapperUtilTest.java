package br.com.dbccompany.votacaocooperado.shared.util;

import br.com.dbccompany.votacaocooperado.web.dto.PautaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ObjectMapperUtilTest {

    @Test
    void givenAnSerializableObjectWhenExecutingTheGenerateJsonMethodThenShouldReturnAnSerializedObject() {
        PautaDto pautaDto = PautaDto.builder().id(1l).descricao("Emissão de novas cotas do fundo Musical").build();
        String jsonReturned = ObjectMapperUtil.generateJson(pautaDto);
        assertEquals("{\"id\":1,\"descricao\":\"Emissão de novas cotas do fundo Musical\"}", jsonReturned);
    }

    @Test
    void givenAnNoSerializableObjectWhenExecutingTheGenerateJsonMethodThenShouldReturnAnNonSerializedObject() {
        Object nonSerializableObject = new Object();
        String jsonReturned = ObjectMapperUtil.generateJson(nonSerializableObject);
        assertEquals(nonSerializableObject.toString(), jsonReturned);
    }
}