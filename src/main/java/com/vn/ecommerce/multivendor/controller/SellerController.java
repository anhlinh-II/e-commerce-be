package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.config.JwtProvider;
import com.vn.ecommerce.multivendor.domain.AccountStatus;
import com.vn.ecommerce.multivendor.exception.SellerException;
import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.modal.SellerReport;
import com.vn.ecommerce.multivendor.modal.VerificationCode;
import com.vn.ecommerce.multivendor.repository.VerificationCodeRepository;
import com.vn.ecommerce.multivendor.request.LoginRequest;
import com.vn.ecommerce.multivendor.response.AuthResponse;
import com.vn.ecommerce.multivendor.service.AuthService;
import com.vn.ecommerce.multivendor.service.impl.EmailService;
import com.vn.ecommerce.multivendor.service.SellerReportService;
import com.vn.ecommerce.multivendor.service.SellerService;
import com.vn.ecommerce.multivendor.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) {
        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_" + email);
        AuthResponse authResponse = authService.signing(req);

        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong otp!");
        }
        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(seller.getEmail());
        verificationCode.setOtp(otp);
        verificationCodeRepository.save(verificationCode);

        String subject = "Ecommerce Email Verification Code";
        String text = "Welcome to our Ecommerce website, verify your email using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(), otp, subject, text + frontend_url);

        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport report =sellerReportService.getSellerReport(seller);

        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false) AccountStatus status) {
        List<Seller> sellers = sellerService.getAllTheSeller(status);

        return ResponseEntity.ok(sellers);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSeller (@RequestHeader("Authorization") String jwt
            , @RequestBody Seller seller) throws Exception {
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);

        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
