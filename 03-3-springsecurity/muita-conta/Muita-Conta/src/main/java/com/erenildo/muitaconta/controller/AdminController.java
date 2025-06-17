package com.erenildo.muitaconta.controller;

import com.erenildo.muitaconta.dtos.user.ListagemUserResonseDTO;
import com.erenildo.muitaconta.service.AdminService;
import com.erenildo.muitaconta.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(
            AdminService adminService,
            UserService userService
    ){
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public Page<ListagemUserResonseDTO> listtarusuarios (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String ordenacao,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataMin,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataMax
    ) {
        return adminService.lisarUser(page, size, ordenacao, nome, dataMin, dataMax);
    }

    @DeleteMapping("/{idUser}")
    public void removerUsuario (@PathVariable String idUser) throws Exception {
        userService.apagarUsuario(idUser);
    }
}
