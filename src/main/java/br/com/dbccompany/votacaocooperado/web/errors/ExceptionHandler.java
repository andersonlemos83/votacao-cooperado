package br.com.dbccompany.votacaocooperado.web.errors;

import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import br.com.dbccompany.votacaocooperado.web.dto.ResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> HandlerDomain(NegocioException exception, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return handleExceptionInternal(exception, responseDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String replace = errors.toString().replace("[", "").replace("]", "");
        ResponseDto responseDto = new ResponseDto(status.value(), replace);
        return handleExceptionInternal(ex, responseDto, new HttpHeaders(), status, request);
    }
}