package org.studyeasy.SpringRest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.studyeasy.SpringRest.model.Account;
import org.studyeasy.SpringRest.payload.auth.AccountDTO;
import org.studyeasy.SpringRest.payload.auth.AccountViewDTO;
import org.studyeasy.SpringRest.payload.auth.AuthoritiesDTO;
import org.studyeasy.SpringRest.payload.auth.PasswordDTO;
import org.studyeasy.SpringRest.payload.auth.ProfileDTO;
import org.studyeasy.SpringRest.payload.auth.TokenDTO;
import org.studyeasy.SpringRest.payload.auth.UserLoginDTO;
import org.studyeasy.SpringRest.service.AccountService;
import org.studyeasy.SpringRest.service.TokenService;
import org.studyeasy.SpringRest.util.constants.AccountError;
import org.studyeasy.SpringRest.util.constants.AccountSuccess;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth Controller", description = "Controller For Account management")
@Slf4j
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDTO> token(@Valid @RequestBody UserLoginDTO userLogin) throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
            return ResponseEntity.ok(new TokenDTO(tokenService.generateToken(authentication)));

        } catch (Exception e) {
            log.debug(AccountError.TOKEN_GENERATION_ERROR.toString() + ": " + e.getMessage());
            return new ResponseEntity(new TokenDTO(null), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value = "users/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "400", description = "enter valid email and password of length 6 to 20 characters long")
    @ApiResponse(responseCode = "201", description = "Account Added")
    @Operation(summary = "add a user to the database")
    public ResponseEntity<String> addUser(@Valid @RequestBody AccountDTO accountDTO) {
        try {
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword(accountDTO.getPassword());
            account.setAuthorities("ROLE_USER");
            accountService.save(account);
            return ResponseEntity.ok(AccountSuccess.ACCOUNT_ADDED.toString());

        } catch (Exception e) {
            log.debug(AccountError.ADD_ACCOUNT_ERROR.toString() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @GetMapping(value = "/users", produces = "application/json")
    @ApiResponse(responseCode = "401", description = "Token Missing")
    @ApiResponse(responseCode = "403", description = "Token Error")
    @Operation(summary = "List User API")
    @SecurityRequirement(name = "studyeasy-demo-api")
    public List<AccountViewDTO> Users() {
        List<AccountViewDTO> accounts = new ArrayList<>();
        for (Account account : accountService.findAll()) {
            accounts.add(new AccountViewDTO(account.getId(), account.getEmail(), account.getAuthorities()));
        }
        return accounts;

    }

    @PutMapping(value = "/users/{user_id}/update-authorities", produces = "application/json", consumes="application/json")
    @ApiResponse(responseCode = "401", description = "Token Missing")
    @ApiResponse(responseCode = "403", description = "Token Error")
    @Operation(summary = "Update Authority API")
    @SecurityRequirement(name = "studyeasy-demo-api")
    public ResponseEntity<AccountViewDTO> updateAuth(@Valid @RequestBody AuthoritiesDTO authoritiesDTO, @PathVariable long user_id) {
        Optional<Account> optionalAccount = accountService.findById(user_id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setAuthorities(authoritiesDTO.getAuthorities());
            accountService.save(account);

            AccountViewDTO accountViewDTO = new AccountViewDTO(account.getId(), account.getEmail(),account.getAuthorities());
            return ResponseEntity.ok(accountViewDTO);
        }
        return new ResponseEntity<AccountViewDTO>(new AccountViewDTO(),HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/profile", produces = "application/json")
    @ApiResponse(responseCode = "401", description = "Token Missing")
    @ApiResponse(responseCode = "403", description = "Token Error")
    @Operation(summary = "List User API")
    @SecurityRequirement(name = "studyeasy-demo-api")
    public ProfileDTO profile(Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(email);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            ProfileDTO profileDTO = new ProfileDTO(account.getId(), account.getEmail(), account.getAuthorities());
            return profileDTO;
        }
        return null;

    }


    @PutMapping(value = "/profile/update-password", produces = "application/json", consumes="application/json")
    @ApiResponse(responseCode = "401", description = "Token Missing")
    @ApiResponse(responseCode = "403", description = "Token Error")
    @Operation(summary = "Update Password API")
    @SecurityRequirement(name = "studyeasy-demo-api")
    public AccountViewDTO updatePassword(@Valid @RequestBody PasswordDTO passwordDTO,Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(email);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setPassword(passwordDTO.getPassword());
            accountService.save(account);

            AccountViewDTO accountViewDTO = new AccountViewDTO(account.getId(), account.getEmail(),account.getAuthorities());
            return accountViewDTO;
        }
        return null;

    }

    @DeleteMapping(value = "/profile/delete")
    @ApiResponse(responseCode = "401", description = "Token Missing")
    @ApiResponse(responseCode = "403", description = "Token Error")
    @Operation(summary = "Delete Profile API")
    @SecurityRequirement(name = "studyeasy-demo-api")
    public ResponseEntity<String> deleteProfile(Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(email);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            accountService.deleteById(account.getId());
            return ResponseEntity.ok("Account Deleteed");
        }
        return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
    }
}
