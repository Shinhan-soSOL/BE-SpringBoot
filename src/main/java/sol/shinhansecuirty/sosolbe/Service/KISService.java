package sol.shinhansecuirty.sosolbe.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sol.shinhansecuirty.sosolbe.DTO.CurrentPriceDTO;

@Service
@RequiredArgsConstructor
public class KISService {

    private final WebClient webClient;
    @Value("${app.key}")
    private String appKey;
    @Value("${app.secretkey}")
    private String appSecret;
    @Value("${kis.access-token}")
    private String accessToken;
    private static final String KIS_BASE_URL = "https://openapi.koreainvestment.com:9443";

    public Integer getCurrentPriceFromKIS(String stockCode) {
        Mono<CurrentPriceDTO> response =webClient.get()
                .uri(KIS_BASE_URL + "/uapi/domestic-stock/v1/quotations/inquire-price?FID_COND_MRKT_DIV_CODE=J&FID_INPUT_ISCD={param}", stockCode)
                .header("authorization", "Bearer " + accessToken)
                .header("appkey", appKey)
                .header("appsecret", appSecret)
                .header("tr_id", "FHKST01010100")
                .retrieve()
                .bodyToMono(CurrentPriceDTO.class);
        CurrentPriceDTO result = response.block();
        return result.getOutput().getStck_prpr();
    }
}
