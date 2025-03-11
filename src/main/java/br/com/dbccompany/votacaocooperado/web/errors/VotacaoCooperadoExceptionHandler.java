package br.com.dbccompany.votacaocooperado.web.errors;

import br.com.dbccompany.votacaocooperado.shared.exception.NegocioException;
import br.com.dbccompany.votacaocooperado.web.dto.ResponseDto;
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

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class VotacaoCooperadoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> HandlerDomain(NegocioException exception, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(BAD_REQUEST.value(), exception.getMessage());
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
        ResponseDto responseDto = new ResponseDto(status.value(), replace);
        return handleExceptionInternal(ex, responseDto, new HttpHeaders(), status, request);
    }
}