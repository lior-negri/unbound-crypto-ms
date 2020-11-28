package com.liornegri.unbound.ms.api.controller.crypto;

import com.liornegri.unbound.ms.service.crypto.CryptoService;
import com.liornegri.unbound.ms.data.model.crypto.request.SignRequest;
import com.liornegri.unbound.ms.data.model.crypto.request.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/crypto")
@Validated
public class CryptoController {
    private final CryptoService service;

    @Autowired
    public CryptoController(final CryptoService service) {
        this.service = service;
    }

    @PostMapping(value = "/keys")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String generateKey() {
        return service.generateKey();
    }

    @DeleteMapping(value = "/keys/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteKey(@NotBlank @PathVariable final String id) {
        service.deleteKey(id);
    }

    @GetMapping(value = "/keys/ids")
    public List<String> getIds() {
        return service.getIds();
    }

    @PostMapping(value = "/signature", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] sign(@Valid @RequestBody final SignRequest request) {
        return service.sign(request.getId(), request.getData());
    }

    @PostMapping(value = "/signature/verified")
    public boolean verifySign(@Valid @RequestBody final VerificationRequest request) {
        return service.verify(request.getId(), request.getData(), request.getSignature());
    }
}
