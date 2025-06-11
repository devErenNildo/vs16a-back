package com.erenildo.fakebank.account.controller;

import com.erenildo.fakebank.account.dtos.*;
import com.erenildo.fakebank.account.security.JwtTokenDecoder;
import com.erenildo.fakebank.account.service.AccountService;
import com.erenildo.fakebank.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JwtTokenDecoder jwtTokenDecoder;
    private final TransactionService transactionService;

    @PostMapping("/register/pix")
    public ResponseEntity<RegisterPixResponseDTO> cadastrarPix(
            @RequestHeader("Authorization") String token,
            @RequestBody RegisterPixRequestDTO dto
    ) throws Exception {

        String userId = jwtTokenDecoder.extractUserId(token);

        return ResponseEntity.ok(accountService.registerPix(dto, userId));
    }

    @PostMapping("/pix")
    public ResponseEntity<PixTransacaoResponseDTO> realizarTransacaoPix(
            @RequestHeader("Authorization") String token,
            @RequestBody PixTransacaoRequestDTO dto
    ) throws Exception {
        String userId = jwtTokenDecoder.extractUserId(token);

        return ResponseEntity.ok(accountService.realizarPix(dto, userId));
    }

    @GetMapping("/pix/recebidos")
    public Page<PixTransactionHistoryDTO> pixRecebidos(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        String userId = jwtTokenDecoder.extractUserId(token);

        return transactionService.buscarPixRecebidos(page, size, userId);
    }

    @GetMapping("/pix/enviados")
    public Page<PixTransactionHistoryDTO> pixEnviados(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        String userId = jwtTokenDecoder.extractUserId(token);

        return transactionService.buscarPixEnviados(page, size, userId);
    }

    @GetMapping("/saldo")
    public ResponseEntity<ConsultaSaldoResponseDTO> consultaSaldo(
            @RequestHeader("Authorization") String token
    ) throws Exception {
        String userId = jwtTokenDecoder.extractUserId(token);
        return ResponseEntity.ok(accountService.saldo(userId));
    }
}
