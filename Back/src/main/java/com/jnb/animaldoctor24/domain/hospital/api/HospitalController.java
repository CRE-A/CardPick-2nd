package com.jnb.animaldoctor24.domain.hospital.api;

import com.jnb.animaldoctor24.domain.hospital.application.HospitalService;
import com.jnb.animaldoctor24.domain.hospital.application.impl.HospitalServiceImpl;
import com.jnb.animaldoctor24.domain.hospital.domain.Hospital;
import com.jnb.animaldoctor24.domain.hospital.dto.HospitalModifyRequest;
import com.jnb.animaldoctor24.domain.hospital.dto.HospitalRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 병원 등록/삭제   [## 관리자만 ##]
// 병원 좋아요(하트) 등록/삭제
// 병원 검색
@RestController
@RequestMapping("/api/v1/hospital")
@RequiredArgsConstructor
@Tag(name="동물병원", description="동물병원 API")
public class HospitalController {
    private final org.slf4j.Logger Logger = LoggerFactory.getLogger(HospitalController.class);

    private final HospitalService hospitalService;

    @GetMapping("/list")
    @Operation(summary = "병원 리스트 조회", description = "병원 목록 조회")
    public ResponseEntity<List<Hospital>> showListOfHospital () {
        return ResponseEntity.ok(hospitalService.list());
    }

    @GetMapping("/{hn}")
    @Operation(summary = "병원 조회", description = "병원 정보 조회")
    public ResponseEntity<Hospital> showHospital (@PathVariable Integer hn) {
        return ResponseEntity.ok().body(hospitalService.getHospital(hn));
    }

    @PostMapping("/new")
    @Operation(summary = "병원 정보 등록", description = "병원 신규 등록")
    public ResponseEntity<ResponseEntity<String>> registerHospital (@RequestBody HospitalRegisterRequest request) {
        return ResponseEntity.ok(hospitalService.register(request));
    }


    @PatchMapping("/{hn}")
    @Operation(summary = "병원 정보 수정", description = "병원 정보 수정")
    public ResponseEntity<ResponseEntity<String>> modifyHospital (@PathVariable Integer hn, @RequestBody HospitalModifyRequest request) {
        return ResponseEntity.ok(hospitalService.modify(request, hn));
    }

    @DeleteMapping("/{hn}")
    @Operation(summary = "병원 정보 삭제", description = "병원 정보 삭제")
    public ResponseEntity<ResponseEntity<String>> deleteHospital (@PathVariable Integer hn) {
        return ResponseEntity.ok(hospitalService.delete(hn));
    }
}
