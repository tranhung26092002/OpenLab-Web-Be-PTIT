package com.example.webtoeic.service.impl;

import com.example.webtoeic.entity.BaiGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.BaiGrammarRepository;
import com.example.webtoeic.service.Impl.BaiGrammarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BaiGrammarServiceImplTest {
    @Mock
    private BaiGrammarRepository baiGrammarRepository;

    @InjectMocks
    private BaiGrammarImpl baiGrammarService;

    @Test
    public void testSaveBaiGrammarSuccess() {
        BaiGrammar mockBaiGrammar = new BaiGrammar();
        // Cấu hình Mockito để mô phỏng hành vi lưu thành công
        when(baiGrammarRepository.save(any(BaiGrammar.class))).thenReturn(mockBaiGrammar);

        BaseResponse result = baiGrammarService.save(mockBaiGrammar);

        assertNotNull(result);
        assertEquals(200, result.getStatus());
        assertEquals("Saved successfully", result.getMessage());
        assertEquals(mockBaiGrammar, result.getData());
    }

    @Test
    public void testSaveBaiGrammarFailure() {
        BaiGrammar mockBaiGrammar = new BaiGrammar();

        RuntimeException exception = new RuntimeException("Save failed");

        doThrow(exception).when(baiGrammarRepository).save(any(BaiGrammar.class));

        BaseResponse result = baiGrammarService.save(mockBaiGrammar);

        assertNotNull(result);
        assertEquals(400, result.getStatus());
        assertEquals("Failed save", result.getMessage());
        assertNull(result.getData());
    }
}

