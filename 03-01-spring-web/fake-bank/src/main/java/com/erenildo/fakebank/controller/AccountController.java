package com.erenildo.fakebank.controller;

import com.erenildo.fakebank.dtos.TransacaoRequestDTO;
import com.erenildo.fakebank.dtos.TransacaoResponseDTO;
import com.erenildo.fakebank.dtos.TransactionHistoryDTO;
import com.erenildo.fakebank.service.PixService;
import com.erenildo.fakebank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final PixService pixService;
    private final TransactionService transactionService;

    @PostMapping("/pix/enviar")
    public ResponseEntity<TransacaoResponseDTO> realizarPix (@RequestBody TransacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pixService.realizarTransacaoPix(dto));
    }

    @GetMapping("/pix/historico/enviado")
    public ResponseEntity<List<TransactionHistoryDTO>> buscarPixRealizado () {
        return ResponseEntity.ok(transactionService.buscarPixRealizados());
    }

    @GetMapping("/pix/historico/recebido")
    public ResponseEntity<List<TransactionHistoryDTO>> buscarPixRecebidos () {
        return ResponseEntity.ok(transactionService.buscarPixRecebidos());
    }

    @GetMapping("/pix/historico/todos")
    public ResponseEntity<List<TransactionHistoryDTO>> buscarPixTodos () {
        return ResponseEntity.ok(transactionService.buscarTodosPixRealizados());
    }
}
