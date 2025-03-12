package br.com.dbccompany.votacaocooperado.web.errors;

import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import br.com.dbccompany.votacaocooperado.web.dto.ResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.dbccompany.votacaocooperado.shared.util.ObjectMapperUtil.generateJson;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Log4j2
@ControllerAdvice
public class VotacaoCooperadoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> HandlerDomain(NegocioException exception, WebRequest request) {
        ResponseDto responseDto = ResponseDto.builder()
                .status(BAD_REQUEST.value())
                .mensagem(exception.getMessage())
                .build();
        log.debug("Saindo de VotacaoCooperadoExceptionHandler: {}", generateJson(responseDto));
        return handleExceptionInternal(exception, responseDto, new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String replace = errors.toString().replace("[", "").replace("]", "");
        ResponseDto responseDto = ResponseDto.builder().status(status.value()).mensagem(replace).build();
        log.debug("Saindo de VotacaoCooperadoExceptionHandler: {}", generateJson(responseDto));
        return handleExceptionInternal(ex, responseDto, new HttpHeaders(), status, request);
    }
}